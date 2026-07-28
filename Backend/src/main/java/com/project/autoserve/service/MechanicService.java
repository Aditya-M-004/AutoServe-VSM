package com.project.autoserve.service;

import com.project.autoserve.dto.mechanic.CreateMechanicRequestDTO;
import com.project.autoserve.dto.mechanic.MechanicResponseDTO;

public interface MechanicService {

    MechanicResponseDTO addMechanic(CreateMechanicRequestDTO request);

}