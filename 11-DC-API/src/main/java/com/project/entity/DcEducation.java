package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CITIZEN_APPS")
@Data
public class DcEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eduId;
    private Long caseNum;
    private String highestQualification;
    private Integer graduationYear;
    private String universityName;
}
