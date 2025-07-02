package com.project.controller;

import com.project.binding.CreateCaseResponse;
import com.project.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CreateCaseRestController {
    
    @Autowired
    private DcService service;
    
    @GetMapping("/case/{appId}")
    public ResponseEntity<CreateCaseResponse> createCase(@PathVariable Integer appId){
        Long caseNum = service.loadCaseNum(appId);
        Map<Integer, String> planNames = service.getPlanNames();

        CreateCaseResponse response = new CreateCaseResponse();
        response.setCaseNum(caseNum);
        response.setPlanNames(planNames);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
