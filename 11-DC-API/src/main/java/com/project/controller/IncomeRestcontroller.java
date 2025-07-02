package com.project.controller;

import com.project.binding.Income;
import com.project.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IncomeRestcontroller {

    @Autowired
    private DcService service;

    @PostMapping("/income")
    public ResponseEntity<Long> saveIncome(@RequestBody Income income){
        Long caseNum = service.saveIncomeData(income);
        return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
    }
}
