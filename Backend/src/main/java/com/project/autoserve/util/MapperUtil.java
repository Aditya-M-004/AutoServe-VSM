package com.project.autoserve.util;

import com.project.autoserve.dto.appointment.AppointmentResponseDTO;
import com.project.autoserve.dto.invoice.InvoiceResponseDTO;
import com.project.autoserve.dto.jobcard.JobCardResponseDTO;
import com.project.autoserve.dto.mechanic.MechanicResponseDTO;
import com.project.autoserve.dto.payment.PaymentResponseDTO;
import com.project.autoserve.dto.vehicle.VehicleResponseDTO;
import com.project.autoserve.entity.Appointment;
import com.project.autoserve.entity.Invoice;
import com.project.autoserve.entity.JobCard;
import com.project.autoserve.entity.Mechanic;
import com.project.autoserve.entity.Payment;
import com.project.autoserve.entity.SparePart;
import com.project.autoserve.entity.Vehicle;

import java.util.Set;
import java.util.stream.Collectors;
import com.project.autoserve.dto.jobcardpart.JobCardPartResponseDTO;
import com.project.autoserve.entity.JobCardPart;

public class MapperUtil {

    private MapperUtil() {
    }

    public static VehicleResponseDTO toVehicleResponse(Vehicle vehicle) {

        return VehicleResponseDTO.builder()
                .vehicleId(vehicle.getVehicleId())
                .vehicleType(vehicle.getVehicleType())
                .vehicleNumber(vehicle.getVehicleNumber())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .fuelType(vehicle.getFuelType())
                .build();
    }

    public static AppointmentResponseDTO toAppointmentResponse(Appointment appointment) {

        return AppointmentResponseDTO.builder()
                .appointmentId(appointment.getAppointmentId())
                .vehicleNumber(appointment.getVehicle().getVehicleNumber())
                .mechanicName(
                        appointment.getMechanic() != null
                                ? appointment.getMechanic().getUser().getName()
                                : "Not Assigned")
                .appointmentDate(appointment.getAppointmentDate())
                .status(appointment.getStatus())
                .problemDescription(appointment.getProblemDescription())
                .build();
    }

    public static MechanicResponseDTO toMechanicResponse(Mechanic mechanic) {

        return MechanicResponseDTO.builder()
                .mechanicId(mechanic.getMechanicId())
                .name(mechanic.getUser().getName())
                .specialization(mechanic.getSpecialization())
                .experience(mechanic.getExperience())
                .availabilityStatus(mechanic.getAvailabilityStatus())
                .build();
    }

    public static JobCardResponseDTO toJobCardResponse(JobCard jobCard) {

        Set<String> spareParts = jobCard.getJobCardParts()
                .stream()
                .map(jobCardPart -> jobCardPart.getSparePart().getPartName())
                .collect(Collectors.toSet());

        return JobCardResponseDTO.builder()
                .jobId(jobCard.getJobId())
                .appointmentId(jobCard.getAppointment().getAppointmentId())
                .inspectionNotes(jobCard.getInspectionNotes())
                .mechanicRemarks(jobCard.getMechanicRemarks())
                .estimatedCost(jobCard.getEstimatedCost())
                .workDone(jobCard.getWorkDone())
                .laborCost(jobCard.getLaborCost())
                .status(jobCard.getStatus())
                .spareParts(spareParts)
                .build();
    }

    public static InvoiceResponseDTO toInvoiceResponse(Invoice invoice) {

        return InvoiceResponseDTO.builder()
                .invoiceId(invoice.getInvoiceId())
                .jobId(invoice.getJobCard().getJobId())
                .partsTotal(invoice.getPartsTotal())
                .laborCost(invoice.getLaborCost())
                .subTotal(invoice.getSubTotal())
                .gstPercentage(invoice.getGstPercentage())
                .gstAmount(invoice.getGstAmount())
                .totalAmount(invoice.getTotalAmount())
                .invoiceDate(invoice.getInvoiceDate())
                .status(invoice.getStatus())
                .build();
    }
    
    public static PaymentResponseDTO toPaymentResponse(Payment payment) {

        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .transactionId(payment.getTransactionId())
                .build();
    }
    
    public static JobCardPartResponseDTO toJobCardPartResponse(JobCardPart jobCardPart) {

        return JobCardPartResponseDTO.builder()
                .jobCardPartId(jobCardPart.getJobCardPartId())
                .jobId(jobCardPart.getJobCard().getJobId())
                .partId(jobCardPart.getSparePart().getPartId())
                .partName(jobCardPart.getSparePart().getPartName())
                .quantity(jobCardPart.getQuantity())
                .unitPrice(jobCardPart.getUnitPrice())
                .subtotal(jobCardPart.getSubtotal())
                .build();
    }

}