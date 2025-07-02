package com.project.controller;

import com.project.binding.CitizenApp;
import com.project.service.ArService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArRestController {

    @Autowired
    private ArService service;

    @PostMapping("/app")
    public ResponseEntity<String> createCitizenApp(@RequestBody CitizenApp app){

        Integer appId = service.createApplication(app);
        if(appId >0){
            return new ResponseEntity<>("App Created with App Id: "+ appId, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Invalid SSN",HttpStatus.BAD_REQUEST);
        }
    }
}
