package com.app.service;

import com.app.binding.CoResponse;
import com.app.entity.CitizenAppEntity;
import com.app.entity.CoTriggerEntity;
import com.app.entity.DcCaseEntity;
import com.app.entity.EligDtlsEntity;
import com.app.repository.CitizenAppRepository;
import com.app.repository.CoTriggerRepository;
import com.app.repository.DcCaseRepository;
import com.app.repository.EligDtlsRepository;
import com.app.util.EmailUtils;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CoServiceImpl implements CoService{

    CitizenAppEntity appEntity = null;

    @Autowired
    private EligDtlsRepository eligDtlsRepository;

    @Autowired
    private CoTriggerRepository coTriggerRepository;

    @Autowired
    private CitizenAppRepository appRepo;

    @Autowired
    private DcCaseRepository dcCaseRepository;

    @Autowired
    private EmailUtils emailUtils;

    @Override
    public CoResponse processPendingTriggers() throws Exception {

        Long failed = 0l;
        Long success = 0l;

        CoResponse response = new CoResponse();

        //fetch al pending triggers
       List<CoTriggerEntity> pendingTrgs = coTriggerRepository.findByTrgStatus("Pending");

       response.setTotalTriggers(Long.valueOf(pendingTrgs.size()));

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorCompletionService<Object> pool = new ExecutorCompletionService<>(executorService);

       for(CoTriggerEntity entity : pendingTrgs){
           pool.submit(new Callable<Object>() {

               @Override
               public Object call() throws Exception{

                   try{
                       processTrigger(response, entity);

                   }catch (Exception e){
                       e.printStackTrace();
                   }
                   return null;
               }
           });
       }

        //process each pending trigger
//        for(CoTriggerEntity entity : pendingTrgs){
//           try{
//               processTrigger(response, entity);
//               success++;
//           }catch (Exception e){
//               e.printStackTrace();
//               failed++;
//           }
//        }

        response.setSuccessTriggers(success);
        response.setFailedTriggers(failed);

        return response;
    }

    private CitizenAppEntity processTrigger(CoResponse response, CoTriggerEntity entity) throws Exception {

        CitizenAppEntity appEntity = null;
        // get eligibility data based on caseNum
        EligDtlsEntity elig = eligDtlsRepository.findByCaseNum(entity.getCaseNum());

        // get citizen data based on caseNum
        Optional<DcCaseEntity> findById = dcCaseRepository.findById(entity.getCaseNum());
        if(findById.isPresent()){
            DcCaseEntity dcCaseEntity = findById.get();
            Integer appId = dcCaseEntity.getAppId();
            Optional<CitizenAppEntity> appEntityOptional = appRepo.findById(appId);
            if(appEntityOptional.isPresent()){
                appEntity = appEntityOptional.get();
            }

        }




            // generate pdf with elig details
            // send pdf to citizen mail
            generateAndSendPdf(elig, appEntity);



        return appEntity;
    }

    private void generateAndSendPdf(EligDtlsEntity eligData, CitizenAppEntity citizenAppEntity) throws Exception{

        Document document = new Document(PageSize.A4);
        File file = new File(eligData.getCaseNum()+ ".pdf");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PdfWriter.getInstance(document, fos);

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Eligibility Report",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f, 3.5f, 3.0f, 1.0f, 3.0f, 1.0f, 3.0f});
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Citizen Name",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Name",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Status",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Start date",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan End date",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Benefit Amount",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Denial Reason",font));
        table.addCell(cell);


        table.addCell(citizenAppEntity.getFullName());
        table.addCell(eligData.getPlanName());
        table.addCell(eligData.getPlanStatus());
        table.addCell(eligData.getPlanStartDate()+"");
        table.addCell(eligData.getPlanEndDate()+"");
        table.addCell(eligData.getBenefitAmount()+"");
        table.addCell(eligData.getDenialReason()+"");


        document.add(table);
        document.close();

        String subject = "HIS Eligibility Info";
        String body = "HIS Eligibility Info";

       // emailUtils.sendEmail(citizenAppEntity.getEmail(), subject, body, file);
        updateTrigger(eligData.getCaseNum(), file);

        file.delete();
    }

    private void updateTrigger(Long caseNum, File file) throws Exception{

        Optional<CoTriggerEntity> coEntityOptional  = coTriggerRepository.findByCaseNum(caseNum);
       if (coEntityOptional.isPresent()){
           CoTriggerEntity coEntity = coEntityOptional.get();
           byte[] arr = new byte[(byte)file.length()];
           FileInputStream fis = new FileInputStream(file);
           coEntity.setCoPdf(arr);
           coEntity.setTrgStatus("Completed");
           coTriggerRepository.save(coEntity);
           fis.close();
       }

    }

}
