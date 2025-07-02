package com.project.controller;

import com.project.binding.PlanSelection;
import com.project.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanSelectionRestController {

    @Autowired
    private DcService service;

    @PostMapping("/planSelection")
    public ResponseEntity<Long> planSelection(PlanSelection planSelection){
        Long caseNum = service.savePlanSelection(planSelection);
        return new ResponseEntity<>(caseNum, HttpStatus.CREATED);
    }
}
