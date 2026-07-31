package com.project.autoserve.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.autoserve.dto.appointment.AppointmentResponseDTO;
import com.project.autoserve.dto.dashboard.AdminDashboardResponseDTO;
import com.project.autoserve.dto.dashboard.CustomerDashboardResponseDTO;
import com.project.autoserve.dto.dashboard.MechanicDashboardResponseDTO;
import com.project.autoserve.dto.payment.PaymentResponseDTO;
import com.project.autoserve.entity.Appointment;
import com.project.autoserve.entity.Mechanic;
import com.project.autoserve.entity.Payment;
import com.project.autoserve.entity.User;
import com.project.autoserve.enums.AppointmentStatus;
import com.project.autoserve.enums.JobStatus;
import com.project.autoserve.enums.Role;
import com.project.autoserve.repository.AppointmentRepository;
import com.project.autoserve.repository.InvoiceRepository;
import com.project.autoserve.repository.JobCardRepository;
import com.project.autoserve.repository.MechanicRepository;
import com.project.autoserve.repository.PaymentRepository;
import com.project.autoserve.repository.UserRepository;
import com.project.autoserve.repository.VehicleRepository;
import com.project.autoserve.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    private final VehicleRepository vehicleRepository;

    private final AppointmentRepository appointmentRepository;

    private final JobCardRepository jobCardRepository;

    private final InvoiceRepository invoiceRepository;

    private final PaymentRepository paymentRepository;

    private final MechanicRepository mechanicRepository;
    
    
    // 1. Public Methods
    
    @Override
    public AdminDashboardResponseDTO getAdminDashboard() {
    	
    	return buildAdminDashboard();
    }

    @Override
    public CustomerDashboardResponseDTO getCustomerDashboard() {

        return null;
    }

    @Override
    public MechanicDashboardResponseDTO getMechanicDashboard() {

        return null;
    }
    
    // 2. Builder Methods
    
    private AdminDashboardResponseDTO buildAdminDashboard() {

        return AdminDashboardResponseDTO.builder()

                .totalCustomers(
                        userRepository.countByRole(Role.CUSTOMER))

                .totalMechanics(
                        userRepository.countByRole(Role.MECHANIC))

                .totalVehicles(
                        vehicleRepository.count())

                .totalAppointments(
                        appointmentRepository.count())

                .pendingAppointments(
                        appointmentRepository.countByStatus(
                                AppointmentStatus.PENDING))

                .completedJobs(
                        jobCardRepository.countByStatus(
                                JobStatus.COMPLETED))

                .totalInvoices(
                        invoiceRepository.count())

                .totalPayments(
                        paymentRepository.count())

                .totalRevenue(
                        paymentRepository.getTotalRevenue())

                .recentAppointments(buildRecentAppointments())

                .recentPayments(buildRecentPayments())

                .build();
    }
    
    private List<PaymentResponseDTO> buildRecentPayments() {

        return paymentRepository
                .findTop5ByOrderByPaymentDateDesc()
                .stream()
                .map(this::mapPayment)
                .toList();
    }
    
    private List<AppointmentResponseDTO> buildRecentAppointments() {

        return appointmentRepository
                .findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(this::mapAppointment)
                .toList();
    }
    
    //3. Helper Methods
    
    private AppointmentResponseDTO mapAppointment(Appointment appointment) {

        return AppointmentResponseDTO.builder()
                .appointmentId(appointment.getAppointmentId())
                .vehicleNumber(
                        appointment.getVehicle().getVehicleNumber())
                .mechanicName(
                        appointment.getMechanic() != null
                                ? appointment.getMechanic()
                                        .getUser()
                                        .getName()
                                : null)
                .appointmentDate(appointment.getAppointmentDate())
                .status(appointment.getStatus())
                .problemDescription(
                        appointment.getProblemDescription())
                .build();
    }
    
    private PaymentResponseDTO mapPayment(Payment payment) {

        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .invoiceId(payment.getInvoice().getInvoiceId())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .transactionId(payment.getTransactionId())
                .paymentStatus(payment.getPaymentStatus())
                .paymentDate(payment.getPaymentDate())
                .build();
    }
    
    private User getLoggedInUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
    


}