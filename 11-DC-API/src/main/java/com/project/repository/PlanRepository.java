package com.project.repository;

import com.project.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface PlanRepository extends JpaRepository<PlanEntity, Serializable> {
}
