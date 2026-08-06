package com.project.autoserve.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.autoserve.dto.appointment.AppointmentRequestDTO;
import com.project.autoserve.dto.appointment.AppointmentResponseDTO;
import com.project.autoserve.entity.Appointment;
import com.project.autoserve.entity.Mechanic;
import com.project.autoserve.entity.User;
import com.project.autoserve.entity.Vehicle;
import com.project.autoserve.enums.AppointmentStatus;
import com.project.autoserve.enums.AvailabilityStatus;
import com.project.autoserve.exception.BadRequestException;
import com.project.autoserve.exception.ResourceNotFoundException;
import com.project.autoserve.repository.AppointmentRepository;
import com.project.autoserve.repository.MechanicRepository;
import com.project.autoserve.repository.UserRepository;
import com.project.autoserve.repository.VehicleRepository;
import com.project.autoserve.service.AppointmentService;
import com.project.autoserve.util.MapperUtil;
import com.project.autoserve.repository.JobCardRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final MechanicRepository mechanicRepository;
    private final JobCardRepository jobCardRepository;

    public AppointmentServiceImpl(
            AppointmentRepository appointmentRepository,
            VehicleRepository vehicleRepository,
            UserRepository userRepository,
            MechanicRepository mechanicRepository,
            JobCardRepository jobCardRepository) {

        this.appointmentRepository = appointmentRepository;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.mechanicRepository = mechanicRepository;
        this.jobCardRepository = jobCardRepository;
    }

    @Override
    public AppointmentResponseDTO bookAppointment(
            AppointmentRequestDTO request,
            String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        Vehicle vehicle = vehicleRepository.findById(request.getVehicleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Vehicle not found."));

        Appointment appointment = new Appointment();

        appointment.setVehicle(vehicle);

        // Mechanic not assigned during booking
        appointment.setMechanic(null);

        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setProblemDescription(request.getProblemDescription());
        appointment.setStatus(AppointmentStatus.PENDING);

        Appointment savedAppointment =
                appointmentRepository.save(appointment);

        return MapperUtil.toAppointmentResponse(savedAppointment);
    }

    @Override
    public List<AppointmentResponseDTO> getMyAppointments(String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        List<Vehicle> vehicles = vehicleRepository.findByUser(user);

        return vehicles.stream()
                .flatMap(vehicle ->
                        appointmentRepository.findByVehicle(vehicle).stream())
                .map(appointment -> {

                    AppointmentResponseDTO dto =
                            MapperUtil.toAppointmentResponse(appointment);

                    jobCardRepository.findByAppointment(appointment)
                            .ifPresent(jobCard ->
                                    dto.setJobId(jobCard.getJobId()));

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentResponseDTO getAppointmentById(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found."));

        return MapperUtil.toAppointmentResponse(appointment);
    }
    
    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(MapperUtil::toAppointmentResponse)
                .toList();
    }
    
    @Override
    public AppointmentResponseDTO assignMechanic(
            Long appointmentId,
            Long mechanicId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found."));

        Mechanic mechanic = mechanicRepository.findById(mechanicId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Mechanic not found."));
        
        if (mechanic.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {

            throw new BadRequestException(
                    "Mechanic is currently "
                            + mechanic.getAvailabilityStatus()
                            + " and cannot be assigned."
            );

        }

        appointment.setMechanic(mechanic);
        
        Appointment updatedAppointment =
                appointmentRepository.save(appointment);

        return MapperUtil.toAppointmentResponse(updatedAppointment);
    }
    
    @Override
    public AppointmentResponseDTO updateAppointmentStatus(
            Long appointmentId,
            AppointmentStatus status) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found."));
        
        if (status == AppointmentStatus.ACCEPTED
                && appointment.getMechanic() != null) {

            Mechanic mechanic = appointment.getMechanic();

            mechanic.setAvailabilityStatus(AvailabilityStatus.BUSY);
            mechanicRepository.save(mechanic);
        }

        appointment.setStatus(status);

     // Release mechanic when job finishes or is cancelled
        if ((status == AppointmentStatus.COMPLETED
                || status == AppointmentStatus.CANCELLED)
                && appointment.getMechanic() != null) {

            Mechanic mechanic = appointment.getMechanic();

            mechanic.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            mechanicRepository.save(mechanic);
        }

     Appointment updatedAppointment = appointmentRepository.save(appointment);

     return MapperUtil.toAppointmentResponse(updatedAppointment);
    }

}