package com.app.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "ELIGIBILITY_DTLS")
public class EligDtlsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer edTraceId;

    private Long caseNum;
    private String holderName;
    private String holderSsn;
    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private Double benefitAmount;
    private String denialReason;
}
