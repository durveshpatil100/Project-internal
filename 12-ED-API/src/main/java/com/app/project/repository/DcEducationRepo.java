package com.app.project.repository;

import com.app.project.entity.DcEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcEducationRepo extends JpaRepository<DcEducation, Serializable> {

    public DcEducation findByCaseNum(Long caseNum);
}
