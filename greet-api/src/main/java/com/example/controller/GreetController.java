package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @Autowired
    private Environment env;

    @GetMapping("/greet")
    public String getGreetMsg(){
        String port= env.getProperty("server.port");
        String msg = "Good evening...!!(from server :: "+ port +")";
        return msg;

    }
}
