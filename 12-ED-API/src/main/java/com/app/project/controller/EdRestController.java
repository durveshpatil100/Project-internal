package com.app.project.controller;

import com.app.project.binding.EligResponse;
import com.app.project.service.EligService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EdRestController {

    @Autowired
    private EligService eligService;

    @GetMapping("/eligibility/{caseNum}")
    public EligResponse determineEligibility(@PathVariable Long caseNum){
        EligResponse eligResponse = eligService.determineEligibility(caseNum);
        return eligResponse;
    }
}
