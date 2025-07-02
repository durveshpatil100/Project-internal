package com.app.controller;

import com.app.binding.CoResponse;
import com.app.service.CoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoRestController {

    @Autowired
    private CoService service;

    @GetMapping("/process")
    public CoResponse processTrigger() throws Exception {
        return service.processPendingTriggers();
    }
}
