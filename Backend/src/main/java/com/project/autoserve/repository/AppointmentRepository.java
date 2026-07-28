package com.project.autoserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.autoserve.entity.Appointment;
import com.project.autoserve.entity.Mechanic;
import com.project.autoserve.entity.Vehicle;
import com.project.autoserve.enums.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByVehicle(Vehicle vehicle);

    List<Appointment> findByMechanic(Mechanic mechanic);

    List<Appointment> findByStatus(AppointmentStatus status);

}