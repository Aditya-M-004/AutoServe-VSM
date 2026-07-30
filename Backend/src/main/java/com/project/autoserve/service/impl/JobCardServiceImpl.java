package com.project.autoserve.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.autoserve.dto.jobcard.CreateJobCardRequestDTO;
import com.project.autoserve.dto.jobcard.JobCardResponseDTO;
import com.project.autoserve.dto.jobcard.UpdateJobCardRequestDTO;
import com.project.autoserve.entity.Appointment;
import com.project.autoserve.entity.JobCard;
import com.project.autoserve.enums.JobStatus;
import com.project.autoserve.exception.DuplicateResourceException;
import com.project.autoserve.exception.ResourceNotFoundException;
import com.project.autoserve.repository.AppointmentRepository;
import com.project.autoserve.repository.JobCardRepository;
import com.project.autoserve.service.JobCardService;
import com.project.autoserve.util.MapperUtil;

@Service
public class JobCardServiceImpl implements JobCardService {

    private final JobCardRepository jobCardRepository;
    private final AppointmentRepository appointmentRepository;

    public JobCardServiceImpl(
            JobCardRepository jobCardRepository,
            AppointmentRepository appointmentRepository) {

        this.jobCardRepository = jobCardRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public JobCardResponseDTO createJobCard(CreateJobCardRequestDTO request) {

        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Appointment not found with ID: " + request.getAppointmentId()));

        if (jobCardRepository.existsByAppointment(appointment)) {
            throw new DuplicateResourceException(
                    "Job Card already exists for this appointment.");
        }

        JobCard jobCard = JobCard.builder()
                .appointment(appointment)
                .inspectionNotes(request.getInspectionNotes())
                .mechanicRemarks(request.getMechanicRemarks())
                .estimatedCost(request.getEstimatedCost())
                .workDone(request.getWorkDone())
                .laborCost(request.getLaborCost())
                .status(request.getStatus())
                .build();

        jobCard = jobCardRepository.save(jobCard);

        return MapperUtil.toJobCardResponse(jobCard);
    }

    @Override
    public JobCardResponseDTO getJobCardById(Long jobId) {

        JobCard jobCard = jobCardRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job Card not found with ID: " + jobId));

        return MapperUtil.toJobCardResponse(jobCard);
    }

    @Override
    public List<JobCardResponseDTO> getAllJobCards() {

        return jobCardRepository.findAll()
                .stream()
                .map(MapperUtil::toJobCardResponse)
                .toList();
    }

    @Override
    public JobCardResponseDTO updateJobCard(
            Long jobId,
            UpdateJobCardRequestDTO request) {

        JobCard jobCard = jobCardRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job Card not found with ID: " + jobId));

        jobCard.setInspectionNotes(request.getInspectionNotes());
        jobCard.setMechanicRemarks(request.getMechanicRemarks());
        jobCard.setEstimatedCost(request.getEstimatedCost());
        jobCard.setWorkDone(request.getWorkDone());
        jobCard.setLaborCost(request.getLaborCost());
        jobCard.setStatus(request.getStatus());

        jobCard = jobCardRepository.save(jobCard);

        return MapperUtil.toJobCardResponse(jobCard);
    }

    @Override
    public void deleteJobCard(Long jobId) {

        JobCard jobCard = jobCardRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job Card not found with ID: " + jobId));

        if (jobCard.getStatus() == JobStatus.COMPLETED) {
            throw new DuplicateResourceException(
                    "Completed Job Cards cannot be deleted.");
        }

        jobCardRepository.delete(jobCard);
    }
}