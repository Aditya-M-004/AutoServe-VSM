package com.project.autoserve.dto.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.project.autoserve.enums.PaymentMethod;
import com.project.autoserve.enums.PaymentStatus;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    private Long paymentId;

    private Long invoiceId;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private String transactionId;

    private PaymentStatus paymentStatus;

    private LocalDateTime paymentDate;

}