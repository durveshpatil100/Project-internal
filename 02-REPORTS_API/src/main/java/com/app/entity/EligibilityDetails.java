package com.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ELIGIBILITY_DETAILS")
@Data
public class EligibilityDetails {

    @Id
    private Integer eligId;
    private String name;
    private Long mobile;
    private String email;
    private Character gender;
    private Long ssn;
    private String planName;
    private String planStatus;
    private LocalDate planStartDate;
    private LocalDate planEndDate;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String createdBy;
    private String updatedBy;
}

