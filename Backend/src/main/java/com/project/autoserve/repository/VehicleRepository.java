package com.project.autoserve.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.autoserve.entity.User;
import com.project.autoserve.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByUser(User user);

    boolean existsByVehicleNumber(String vehicleNumber);
    
    long count();

}