package com.app.project.repository;

import com.app.project.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PlanRepository extends JpaRepository<PlanEntity, Serializable> {
}
