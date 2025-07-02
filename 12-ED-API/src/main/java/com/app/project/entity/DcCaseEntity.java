package com.app.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DC_CASES")
@Data
public class DcCaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseNum;
    private Integer appId;
    private Integer planId;



}
