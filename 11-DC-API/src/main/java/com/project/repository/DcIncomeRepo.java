package com.project.repository;

import com.project.entity.DcIncomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcIncomeRepo extends JpaRepository<DcIncomeEntity, Serializable> {

    public DcIncomeEntity findByCaseNum(Long caseNum);
}
