package com.example.welcome_api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "greet-api")
public interface GreetClient {

    @GetMapping("/greet")
    public String invokeGreetApi();
}
