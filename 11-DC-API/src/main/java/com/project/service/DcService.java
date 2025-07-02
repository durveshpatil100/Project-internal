package com.project.service;


import com.project.binding.*;

import java.util.List;
import java.util.Map;

public interface DcService {

    public Long loadCaseNum(Integer appId);

    public Map<Integer,String> getPlanNames();

    public Long savePlanSelection(PlanSelection planSelection);

    public Long saveIncomeData(Income income);

    public Long saveEducation(Education education);

    public Long saveChildren(ChildRequest request);

    public DcSummary getSummary(Long caseNumber);
}
