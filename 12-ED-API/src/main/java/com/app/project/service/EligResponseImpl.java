package com.app.project.service;

import com.app.project.binding.EligResponse;
import com.app.project.entity.*;
import com.app.project.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class EligResponseImpl implements EligService{

    @Autowired
    private DcCaseRepo dcCaseRepo;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private DcIncomeRepo incomeRepository;

    @Autowired
    private DcChildrenRepo dcChildrenRepo;

    @Autowired
    private CitizenAppRepository appRepository;

    @Autowired
    private DcEducationRepo dcEducationRepo;

    @Autowired
    private EligDtlsRepository eligDtlsRepository;

    @Autowired
    private CoTriggerRepository coTriggerRepository;

    @Override
    public EligResponse determineEligibility(Long caseNum) {
        Optional<DcCaseEntity> caseEntity = dcCaseRepo.findById(caseNum);
        Integer planId = null;
        String planName = null;
        Integer appId = null;

        if(caseEntity.isPresent()){
            DcCaseEntity dcCaseEntity = caseEntity.get();
             planId = dcCaseEntity.getPlanId();
             appId = dcCaseEntity.getAppId();

        }

            Optional<PlanEntity> planEntity = planRepository.findById(planId);
            if (planEntity.isPresent()) {
                PlanEntity plan = planEntity.get();
                planName = plan.getPlanName();
            }


        Optional<CitizenAppEntity> app = appRepository.findById(appId);
        Integer age =0;
        CitizenAppEntity citizenAppEntity = null;
        if(app.isPresent()) {
             citizenAppEntity = app.get();
            LocalDate dob = citizenAppEntity.getDob();
            LocalDate now = LocalDate.now();
            age = Period.between(dob, now).getYears();
        }

        EligResponse eligResponse= executePlanConditions(caseNum, planName, age);

        //logic to store data in dab
        EligDtlsEntity eligDtlsEntity = new EligDtlsEntity();
        BeanUtils.copyProperties(eligResponse, eligDtlsEntity);

        eligDtlsEntity.setCaseNum(caseNum);
        eligDtlsEntity.setHolderName(citizenAppEntity.getFullName());
        eligDtlsEntity.setHolderSsn(citizenAppEntity.getSsn());

        eligDtlsRepository.save(eligDtlsEntity);

        CoTriggerEntity coTriggerEntity = new CoTriggerEntity();
        coTriggerEntity.setCaseNum(caseNum);
        coTriggerEntity.setTriggerStatus("Pending");

        coTriggerRepository.save(coTriggerEntity);

        return eligResponse;
    }

    private EligResponse executePlanConditions(Long caseNum, String planName, Integer age){

        EligResponse response = new EligResponse();
        DcIncomeEntity income = incomeRepository.findByCaseNum(caseNum);

        if("SNAP".equals(planName)){

            Double empIncome = income.getEmpIncome();
            if(empIncome <=300){
                response.setPlanStatus("APPROVED");
            }else{
                response.setPlanStatus("DENIED");
                response.setDenialReason("High Income");
            }

        }else if ("CCAP".equals(planName)){
            boolean ageCondition = true;
            boolean kidsCountCondition = false;

            List<DcChildren> childs = dcChildrenRepo.findByCaseNum(caseNum);
            if(!childs.isEmpty()){
                kidsCountCondition =true;
                for (DcChildren entity : childs){
                    Integer childAge = entity.getChildAge();
                    if(childAge > 16){
                        ageCondition = false;
                        break;
                    }
                }
            }
            if(income.getEmpIncome() <= 300 && kidsCountCondition && ageCondition){
                response.setPlanStatus("APPROVED");
            }else{
                response.setPlanStatus("DENIED");
                response.setDenialReason("Not satisfied business rules");
            }

        }else if ("Medicaid".equals(planName)){

            Double empIncome= income.getEmpIncome();
            Double propertyIncome = income.getPropertyIncome();

            if (empIncome <= 300 && propertyIncome == 0) {
                response.setPlanStatus("APPROVED");
            }else{
                response.setPlanStatus("DENIED");
                response.setDenialReason("High Income");
            }

        }else if ("Medicare".equals(planName)){

                if(age>= 65){
                    response.setPlanStatus("APPROVED");
                }else{
                    response.setPlanStatus("DENIED");
                    response.setDenialReason("AGE not Matched");
                }



        }else if ("NJW".equals(planName)){
            DcEducation educationEntity = dcEducationRepo.findByCaseNum(caseNum);
            Integer graduationYear = educationEntity.getGraduationYear();
            int currentYear = LocalDate.now().getYear();

            if(income.getEmpIncome() <=0 && graduationYear < currentYear){
                response.setPlanStatus("APPROVED");
            }else{
                response.setPlanStatus("DENIED");
                response.setDenialReason("Rules not satisfied");
            }
        }

        if (response.getPlanStatus().equals("APPROVED")){
            response.setPlanStartDate(LocalDate.now());
            response.setPlanEndDate(LocalDate.now().plusMonths(6));
            response.setBenefitAmount(350.00);
        }

        return response;
    }
}
