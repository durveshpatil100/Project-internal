package com.project.controller;

import com.project.binding.ChildRequest;
import com.project.binding.DcSummary;
import com.project.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildRestController {

    @Autowired
    private DcService dcservice;

    @PostMapping("/childrens")
    public ResponseEntity<DcSummary> saveChilds(@RequestBody ChildRequest request){

        Long caseNum = dcservice.saveChildren(request);
        DcSummary summary = dcservice.getSummary(caseNum);

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }
}
