package com.app.repository;

import com.app.entity.DcCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcCaseRepository extends JpaRepository<DcCaseEntity, Serializable> {
}
