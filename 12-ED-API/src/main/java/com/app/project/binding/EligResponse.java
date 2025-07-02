package com.app.project.binding;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EligResponse {

    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Double benefitAmount;
    private String denialReason;
}
