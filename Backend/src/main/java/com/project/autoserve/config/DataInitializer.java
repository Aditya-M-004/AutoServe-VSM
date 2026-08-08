package com.project.autoserve.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.autoserve.entity.SparePart;
import com.project.autoserve.entity.User;
import com.project.autoserve.enums.Role;
import com.project.autoserve.enums.UserStatus;
import com.project.autoserve.repository.SparePartRepository;
import com.project.autoserve.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SparePartRepository sparePartRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            SparePartRepository sparePartRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.sparePartRepository = sparePartRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        initializeAdmin();

        initializeSpareParts();
    }

    // ==============================
    // DEFAULT ADMIN
    // ==============================

    private void initializeAdmin() {

        if (!userRepository.existsByEmail("admin@autoserve.com")) {

            User admin = User.builder()
                    .name("System Administrator")
                    .email("admin@autoserve.com")
                    .phone("9876543210")
                    .password(passwordEncoder.encode("Admin@123"))
                    .role(Role.ADMIN)
                    .status(UserStatus.ACTIVE)
                    .build();

            userRepository.save(admin);

            System.out.println("=======================================");
            System.out.println("Default Admin Created Successfully");
            System.out.println("Email : admin@autoserve.com");
            System.out.println("Password : Admin@123");
            System.out.println("=======================================");

        } else {

            System.out.println("Default Admin Already Exists.");
        }
    }

    // ==============================
    // DEFAULT SPARE PARTS
    // ==============================

    private void initializeSpareParts() {

        if (sparePartRepository.count() == 0) {

            List<SparePart> spareParts = List.of(

                    SparePart.builder()
                            .partName("Brake Pad")
                            .unitPrice(new BigDecimal("1800.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Engine Oil")
                            .unitPrice(new BigDecimal("750.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Air Filter")
                            .unitPrice(new BigDecimal("450.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Radiator Hose")
                            .unitPrice(new BigDecimal("950.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Spark Plug")
                            .unitPrice(new BigDecimal("250.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Clutch Plate")
                            .unitPrice(new BigDecimal("3500.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Oil Filter")
                            .unitPrice(new BigDecimal("300.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Headlight Bulb")
                            .unitPrice(new BigDecimal("450.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Battery")
                            .unitPrice(new BigDecimal("5200.00"))
                            .build(),

                    SparePart.builder()
                            .partName("Coolant")
                            .unitPrice(new BigDecimal("600.00"))
                            .build()
            );

            sparePartRepository.saveAll(spareParts);

            System.out.println("=======================================");
            System.out.println("Default Spare Parts Created Successfully");
            System.out.println("Total Spare Parts : " + spareParts.size());
            System.out.println("=======================================");

        } else {

            System.out.println("Spare Parts Already Exist. Skipping Initialization.");
        }
    }
}