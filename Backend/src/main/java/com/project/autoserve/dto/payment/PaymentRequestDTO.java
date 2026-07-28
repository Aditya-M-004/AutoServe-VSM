package com.project.autoserve.dto.payment;

import com.project.autoserve.enums.PaymentMethod;

import lombok.Data;

@Data
public class PaymentRequestDTO {

    private Long invoiceId;

    private PaymentMethod paymentMethod;
}