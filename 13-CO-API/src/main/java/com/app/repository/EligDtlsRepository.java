package com.app.repository;

import com.app.entity.EligDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface EligDtlsRepository extends JpaRepository<EligDtlsEntity, Serializable> {

    public EligDtlsEntity findByCaseNum(Long caseNum);
}
