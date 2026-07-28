package com.project.autoserve.dto.invoice;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceResponseDTO {

    private Long invoiceId;

    private BigDecimal finalCost;

    private BigDecimal gst;

    private BigDecimal totalCost;

    private LocalDate invoiceDate;
}