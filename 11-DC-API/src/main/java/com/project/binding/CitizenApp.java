package com.project.binding;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class CitizenApp {

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
