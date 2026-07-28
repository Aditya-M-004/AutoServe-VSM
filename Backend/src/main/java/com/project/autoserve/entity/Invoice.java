package com.project.autoserve.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false, unique = true)
    private JobCard jobCard;

    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private BigDecimal finalCost;

    @DecimalMin(value = "0.0")
    private BigDecimal gst;

    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private BigDecimal totalCost;

    @Column(nullable = false)
    private LocalDate invoiceDate;
}