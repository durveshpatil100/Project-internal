package com.project.service;

import com.project.binding.CitizenApp;
import com.project.entity.CitizenAppEntity;
import com.project.repository.CitizenAppRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ArServiceImpl implements ArService{

    @Autowired
    private CitizenAppRepository appRepository;

    @Override
    public Integer createApplication(CitizenApp app) {

        String endPointUrl = "https://ssa-web-api.herokuapp.com/ssn/{ssn}";

//        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> resEntity = rt.getForEntity(endPointUrl, String.class, app.getSsn());
//        String stateName = resEntity.getBody();

        WebClient webClient = WebClient.create();

       String stateName =  webClient.get()
                .uri(endPointUrl, app.getSsn())
                .retrieve()
                .bodyToMono(String.class)
                .block();   //to make synchronous call

        if("New Jersy".equals(stateName)){
            //create application

            CitizenAppEntity entity = new CitizenAppEntity();
            BeanUtils.copyProperties(app, entity);

            entity.setStateName(stateName);

            CitizenAppEntity save = appRepository.save(entity);

            return save.getAppId();
        }

        return 0;
    }
}
