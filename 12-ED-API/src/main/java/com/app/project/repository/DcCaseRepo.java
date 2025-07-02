package com.app.project.repository;

import com.app.project.entity.DcCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface DcCaseRepo extends JpaRepository<DcCaseEntity, Serializable> {

    public DcCaseEntity findByAppId(Integer appId);
}
