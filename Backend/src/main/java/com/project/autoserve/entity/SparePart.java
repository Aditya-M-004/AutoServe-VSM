package com.project.autoserve.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "spare_parts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;

    @NotBlank
    @Column(nullable = false)
    private String partName;

    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private BigDecimal unitPrice;

    @ManyToMany(mappedBy = "spareParts")
    private Set<JobCard> jobCards = new HashSet<>();

}