package com.example.welcome_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WelcomeRestController {

    @Autowired
    private GreetClient greetClient;

    @GetMapping("/welcome")
    public WelcomeResponse getWelcomeMsg(){
        String welcomeMsg = "Welcome !!!!";

        //inter service communication
        String greetMsg = greetClient.invokeGreetApi();

        //external service communication
        RestTemplate rt = new RestTemplate();
        String petEndpointUrl = "https://fbqm3v39o8.execute-api.ap-south-1.amazonaws.com/dev/pets/1";
        ResponseEntity<Pet> petEntity = rt.getForEntity(petEndpointUrl, Pet.class);
        Pet petData = petEntity.getBody();

        WelcomeResponse finalResponse = new WelcomeResponse();
        finalResponse.setWelcomeMsg(greetMsg);
        finalResponse.setGreetMsg(welcomeMsg);
        finalResponse.setPet(petData);
       // return greetMsg+" "+welcomeMsg;
        return finalResponse;
    }
}
