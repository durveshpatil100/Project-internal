package com.app.project.repository;

import com.app.project.entity.EligDtlsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface EligDtlsRepository extends JpaRepository<EligDtlsEntity, Serializable> {
}
