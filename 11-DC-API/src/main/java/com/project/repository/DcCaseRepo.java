package com.project.repository;

import com.project.entity.DcCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcCaseRepo extends JpaRepository<DcCaseEntity, Serializable> {

    public DcCaseEntity findByAppId(Integer appId);
}
