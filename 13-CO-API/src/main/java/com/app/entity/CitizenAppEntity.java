package com.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "CITIZEN_APPS")
@Data
public class CitizenAppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appId;

    private String fullName;

    private String email;

    private Long phno;

    private String gender;

    private String ssn;

    private String stateName;

    @CreationTimestamp
    private LocalDate dob;

    @CreationTimestamp
    private LocalDate createDate;

    private String createdBy;

    private String updatedBy;
}
