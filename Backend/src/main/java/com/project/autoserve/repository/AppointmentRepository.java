package com.project.autoserve.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.autoserve.entity.Appointment;
import com.project.autoserve.entity.Mechanic;
import com.project.autoserve.entity.User;
import com.project.autoserve.entity.Vehicle;
import com.project.autoserve.enums.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByVehicle(Vehicle vehicle);

    List<Appointment> findByMechanic(Mechanic mechanic);

    List<Appointment> findByStatus(AppointmentStatus status);
    
    List<Appointment> findByVehicleIn(List<Vehicle> vehicles);
    
    long countByStatus(AppointmentStatus status);

    long countByMechanic(Mechanic mechanic);

    long countByVehicleUser(User user);
    
    List<Appointment> findTop5ByOrderByCreatedAtDesc();
    
    Optional<Appointment> findFirstByVehicleUserAndAppointmentDateGreaterThanEqualOrderByAppointmentDateAsc(
            User user,
            LocalDate appointmentDate
    );
    
    long countByMechanicAndAppointmentDate(
            Mechanic mechanic,
            LocalDate appointmentDate
    );
    
    List<Appointment> findTop5ByMechanicOrderByAppointmentDateDesc(
            Mechanic mechanic
    );

}