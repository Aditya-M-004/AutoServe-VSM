package com.project.autoserve.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.autoserve.entity.Appointment;
import com.project.autoserve.entity.JobCard;

public interface JobCardRepository extends JpaRepository<JobCard, Long> {

    boolean existsByAppointment(Appointment appointment);

    Optional<JobCard> findByAppointment(Appointment appointment);
}