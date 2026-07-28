package com.project.autoserve.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.autoserve.dto.mechanic.CreateMechanicRequestDTO;
import com.project.autoserve.dto.mechanic.MechanicResponseDTO;
import com.project.autoserve.entity.Mechanic;
import com.project.autoserve.entity.User;
import com.project.autoserve.enums.Role;
import com.project.autoserve.enums.UserStatus;
import com.project.autoserve.exception.DuplicateResourceException;
import com.project.autoserve.repository.MechanicRepository;
import com.project.autoserve.repository.UserRepository;
import com.project.autoserve.service.MechanicService;
import com.project.autoserve.util.MapperUtil;

@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MechanicServiceImpl(
            MechanicRepository mechanicRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.mechanicRepository = mechanicRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public MechanicResponseDTO addMechanic(CreateMechanicRequestDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered.");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.MECHANIC)
                .status(UserStatus.ACTIVE)
                .build();

        user = userRepository.save(user);

        Mechanic mechanic = Mechanic.builder()
                .user(user)
                .specialization(request.getSpecialization())
                .experience(request.getExperience())
                .availabilityStatus(request.getAvailabilityStatus())
                .build();

        mechanic = mechanicRepository.save(mechanic);

        return MapperUtil.toMechanicResponse(mechanic);
    }

}