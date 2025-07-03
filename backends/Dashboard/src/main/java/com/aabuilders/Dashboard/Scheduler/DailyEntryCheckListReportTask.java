package com.aabuilders.Dashboard.Scheduler;

import com.aabuilders.Dashboard.Entity.DailyChecklistEntry;
import com.aabuilders.Dashboard.Entity.ExpensesForm;
import com.aabuilders.Dashboard.Entity.Res;
import com.aabuilders.Dashboard.Model.PdfGenerator;
import com.aabuilders.Dashboard.Repository.DailyChecklistEntryRepo;
import com.aabuilders.Dashboard.Repository.ExpensesRepo;
import com.aabuilders.Dashboard.Service.EntryCheckListGoogleDriveUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DailyEntryCheckListReportTask {

    @Autowired
    private ExpensesRepo expensesRepo;

    @Autowired
    private EntryCheckListGoogleDriveUploadService driveUploadService;

    @Autowired
    private DailyChecklistEntryRepo dailyChecklistEntryRepo;

    @Scheduled(cron = "0 05 17 * * ?")
    public void generateAndUploadDailyPdf() {
        try {
            int checklistNumber = driveUploadService.getNextChecklistNumber();
            List<ExpensesForm> entries = expensesRepo.findTodayEntries();
            if (entries.isEmpty()) return;

            for (ExpensesForm e : entries) {
                e.setDailyChecklistNo(String.valueOf(checklistNumber));
                expensesRepo.save(e);

                DailyChecklistEntry entry = new DailyChecklistEntry();
                entry.setTimestamp(e.getTimestamp().plusMinutes(330));
                entry.setDate(e.getDate());
                entry.setSiteName(e.getSiteName());
                entry.setVendor(e.getVendor());
                entry.setContractor(e.getContractor());
                entry.setQuantity(e.getQuantity());
                entry.setAmount(e.getAmount());
                entry.setCategory(e.getCategory());
                entry.setEno(e.getENo());
                entry.setChecklistNumber(checklistNumber);
                dailyChecklistEntryRepo.save(entry);
            }

            List<DailyChecklistEntry> savedEntries = dailyChecklistEntryRepo.findByChecklistNumber(checklistNumber);
            byte[] pdfData = PdfGenerator.generateChecklistPdf(savedEntries, checklistNumber);
            String fileName = checklistNumber + "# Entry Checklist " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".pdf";

            Res response = driveUploadService.uploadPdfToDrive(new ByteArrayInputStream(pdfData), fileName);

            if (response.getStatus() == 200) {
                System.out.println("PDF uploaded to Drive: " + response.getUrl());
                savedEntries.forEach(entry -> entry.setEntryChecklistUrl(response.getUrl()));
                dailyChecklistEntryRepo.saveAll(savedEntries);
            } else {
                System.err.println("Drive upload failed: " + response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateChecklistForYesterday() {
        try {
            LocalDate targetDate = LocalDate.of(2025, 5, 1); // Can be made dynamic if needed
            System.out.println(targetDate + " Pdf Generation schedule is started!");
            int checklistNumber = driveUploadService.getNextChecklistNumber();
            List<ExpensesForm> entries = expensesRepo.findBySpecificDate(targetDate);
            if (entries.isEmpty()) {
                System.out.println("No entries found for date: " + targetDate);
                return;
            }

            for (ExpensesForm e : entries) {
                e.setDailyChecklistNo(String.valueOf(checklistNumber));
                expensesRepo.save(e);

                DailyChecklistEntry entry = new DailyChecklistEntry();
                entry.setTimestamp(e.getTimestamp().plusMinutes(330));
                entry.setDate(e.getDate());
                entry.setSiteName(e.getSiteName());
                entry.setVendor(e.getVendor());
                entry.setContractor(e.getContractor());
                entry.setQuantity(e.getQuantity());
                entry.setAmount(e.getAmount());
                entry.setCategory(e.getCategory());
                entry.setEno(e.getENo());
                entry.setChecklistNumber(checklistNumber);
                dailyChecklistEntryRepo.save(entry);
            }

            List<DailyChecklistEntry> savedEntries = dailyChecklistEntryRepo.findByChecklistNumber(checklistNumber);
            byte[] pdfData = PdfGenerator.generateChecklistPdf(savedEntries, checklistNumber);
            String fileName = checklistNumber + "# Entry Checklist " + targetDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".pdf";

            Res response = driveUploadService.uploadPdfToDrive(new ByteArrayInputStream(pdfData), fileName);

            if (response.getStatus() == 200) {
                System.out.println("✅ PDF uploaded to Drive: " + response.getUrl());
                savedEntries.forEach(entry -> entry.setEntryChecklistUrl(response.getUrl()));
                dailyChecklistEntryRepo.saveAll(savedEntries);
            } else {
                System.err.println("❌ Drive upload failed: " + response.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
