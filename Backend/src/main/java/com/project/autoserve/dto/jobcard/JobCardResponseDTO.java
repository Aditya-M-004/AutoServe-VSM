package com.project.autoserve.dto.jobcard;

import java.math.BigDecimal;
import java.util.Set;

import com.project.autoserve.enums.JobStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobCardResponseDTO {

    private Long jobId;

    private String inspectionNotes;

    private BigDecimal estimatedCost;

    private String workDone;

    private BigDecimal laborCost;

    private JobStatus status;

    private Set<String> spareParts;
}