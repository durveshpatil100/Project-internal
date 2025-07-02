package com.app.project.repository;

import com.app.project.entity.DcIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcIncomeRepo extends JpaRepository<DcIncomeEntity, Serializable> {

    public DcIncomeEntity findByCaseNum(Long caseNum);
}
