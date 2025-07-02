package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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

    private Long ssn;

    private String stateName;

    private Date dob;

    @CreationTimestamp
    private Date createDate;

    @CreationTimestamp
    private String createdBy;

    private String updatedBy;
}
