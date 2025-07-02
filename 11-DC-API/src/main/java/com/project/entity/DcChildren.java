package com.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "DC_CHILDREN")
@Data
public class DcChildren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer childId;
    private Integer childAge;
    private Long childSsn;
    private Long caseNum;
    private String childName;

}
