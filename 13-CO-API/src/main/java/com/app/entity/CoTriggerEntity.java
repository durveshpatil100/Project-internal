package com.app.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "CO_TRIGGERS")
public class CoTriggerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trgId;

    private Long caseNum;

    @Lob
    private byte[] coPdf;
    private String trgStatus;
}