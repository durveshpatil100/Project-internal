package com.app.project.service;

import com.app.project.binding.EligResponse;

public interface EligService {

    public EligResponse determineEligibility(Long caseNum);
}
