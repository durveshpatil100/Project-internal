package com.app.repository;

import com.app.entity.CoTriggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CoTriggerRepository extends JpaRepository<CoTriggerEntity, Serializable> {

    public List<CoTriggerEntity> findByTrgStatus(String status);

    Optional<CoTriggerEntity> findByCaseNum(Long caseNum);
}
