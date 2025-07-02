package com.app.repository;

import com.app.entity.CitizenAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {
}
