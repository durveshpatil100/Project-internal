package com.app.project.repository;

import com.app.project.entity.DcChildren;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface DcChildrenRepo extends JpaRepository<DcChildren, Serializable> {

    public List<DcChildren> findByCaseNum(Long caseNum);
}
