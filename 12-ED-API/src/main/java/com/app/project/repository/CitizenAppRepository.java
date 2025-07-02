package com.app.project.repository;

import com.app.project.entity.CitizenAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {
}
