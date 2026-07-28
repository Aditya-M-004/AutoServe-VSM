package com.project.autoserve.dto.payment;

import java.math.BigDecimal;

import com.project.autoserve.enums.PaymentStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponseDTO {

    private Long paymentId;

    private BigDecimal amount;

    private PaymentStatus paymentStatus;

    private String transactionId;
}