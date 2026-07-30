package com.project.autoserve.dto.jobcard;

import java.math.BigDecimal;
import java.util.Set;

import com.project.autoserve.enums.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobCardResponseDTO {

    private Long jobId;

    private Long appointmentId;

    private String inspectionNotes;

    private String mechanicRemarks;

    private BigDecimal estimatedCost;

    private String workDone;

    private BigDecimal laborCost;

    private JobStatus status;

    private Set<String> spareParts;
}