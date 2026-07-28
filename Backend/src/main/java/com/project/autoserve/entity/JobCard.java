package com.project.autoserve.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.project.autoserve.enums.JobStatus;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

@Entity
@Table(name = "job_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    private Appointment appointment;

    @Column(length = 500)
    private String inspectionNotes;

    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private BigDecimal estimatedCost;

    @Column(length = 1000)
    private String workDone;

    @DecimalMin(value = "0.0")
    private BigDecimal laborCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status;

    @ManyToMany
    @JoinTable(
            name = "jobcard_parts",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "part_id")
    )
    private Set<SparePart> spareParts = new HashSet<>();

}