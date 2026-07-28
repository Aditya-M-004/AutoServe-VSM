package com.project.autoserve.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.autoserve.dto.mechanic.CreateMechanicRequestDTO;
import com.project.autoserve.dto.mechanic.MechanicResponseDTO;
import com.project.autoserve.service.MechanicService;
import com.project.autoserve.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {

    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MechanicResponseDTO>> addMechanic(
            @Valid @RequestBody CreateMechanicRequestDTO request) {

        MechanicResponseDTO response = mechanicService.addMechanic(request);

        ApiResponse<MechanicResponseDTO> apiResponse =
                ApiResponse.<MechanicResponseDTO>builder()
                        .success(true)
                        .message("Mechanic added successfully.")
                        .data(response)
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse);
    }

}