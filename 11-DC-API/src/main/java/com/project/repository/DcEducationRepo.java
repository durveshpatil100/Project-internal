package com.project.repository;

import com.project.entity.DcEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcEducationRepo extends JpaRepository<DcEducation, Serializable> {

    public DcEducation findByCaseNum(Long caseNum);
}
