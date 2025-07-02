package com.project.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.project.binding.*;
import com.project.entity.*;
import com.project.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DcServiceImpl implements DcService{

    @Autowired
    private DcCaseRepo dcCaseRepo;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private DcIncomeRepo dcIncomeRepo;
    
    @Autowired
    private DcEducationRepo dcEducationRepo;
    
    @Autowired
    private DcChildrenRepo dcChildrenRepo;

    @Autowired
    private CitizenAppRepository appRepository;

    @Override
    public Long loadCaseNum(Integer appId) {

        Optional<CitizenAppEntity> app = appRepository.findById(appId);

        if(app.isPresent()){
            DcCaseEntity entity = new DcCaseEntity();
            entity.setAppId(appId);

            entity = dcCaseRepo.save(entity);

            return entity.getCaseNum();
        }
       return 0l;
    }

    @Override
    public Map<Integer,String> getPlanNames() {
        List<PlanEntity> findAll = planRepository.findAll();
        Map<Integer,String> plansMap = new HashMap<>();

        for(PlanEntity entity : findAll){
            plansMap.put(entity.getPlanId(),entity.getPlanName());
        }
        return plansMap;
    }

    @Override
    public Long savePlanSelection(PlanSelection planSelection) {

        Optional<DcCaseEntity> findById = dcCaseRepo.findById(planSelection.getCaseNum());

        if(findById.isPresent()){
            DcCaseEntity dcCaseEntity = findById.get();
            dcCaseEntity.setPlanId(planSelection.getPlanId());

            dcCaseRepo.save(dcCaseEntity);
            return planSelection.getCaseNum();
        }
        return null;
    }

    @Override
    public Long saveIncomeData(Income income) {
        DcIncomeEntity entity = new DcIncomeEntity();
        BeanUtils.copyProperties(income,entity);
        
        dcIncomeRepo.save(entity);
        return income.getCaseNum();
    }

    @Override
    public Long saveEducation(Education education) {
        DcEducation entity = new DcEducation();
        BeanUtils.copyProperties(education, entity);
        dcEducationRepo.save(entity);
        return education.getCaseNum();
    }

    @Override
    public Long saveChildren(ChildRequest request) {
        List<Child> childs = request.getChilds();
        Long caseNum = request.getCaseNum();

        for(Child c : childs){
            DcChildren entity = new DcChildren();
            BeanUtils.copyProperties(c , entity);
            entity.setCaseNum(caseNum);
            dcChildrenRepo.save(entity);
        }
       // return childs.get(0).getCaseNum();
        return request.getCaseNum();
    }

    @Override
    public DcSummary getSummary(Long caseNum) {

        String planName = "";

        DcIncomeEntity incomeEntity = dcIncomeRepo.findByCaseNum(caseNum);
        DcEducation educationEntity = dcEducationRepo.findByCaseNum(caseNum);
        List<DcChildren> childsEntity = dcChildrenRepo.findByCaseNum(caseNum);
        Optional<DcCaseEntity> dcCaseEntity = dcCaseRepo.findById(caseNum);

        if(dcCaseEntity.isPresent()){
            Integer planId = dcCaseEntity.get().getPlanId();
            Optional<PlanEntity> plan = planRepository.findById(planId);
            if(plan.isPresent()){
                 planName = plan.get().getPlanName();
            }
        }

        // set the data to summary obj
        DcSummary summary = new DcSummary();
        summary.setPlanName(planName);

        Income income = new Income();
        BeanUtils.copyProperties(incomeEntity, income);
        summary.setIncome(income);

        Education edu = new Education();
        BeanUtils.copyProperties(educationEntity, edu);
        summary.setEducation(edu);

        List<Child> childs = new ArrayList<>();
        for(DcChildren entity : childsEntity){
            Child ch = new Child();
            BeanUtils.copyProperties(entity, ch);
            childs.add(ch);
        }
        summary.setChilds(childs);
        return summary;
    }
}
