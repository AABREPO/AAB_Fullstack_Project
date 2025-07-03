package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.ExpensesForm;
import com.aabuilders.Dashboard.Entity.SiteNamesWithSiteNo;
import com.aabuilders.Dashboard.Repository.ExpensesRepo;
import com.aabuilders.Dashboard.Repository.SiteNameWithSiteNoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class SiteNameSiteNoService {

    @Autowired
    private SiteNameWithSiteNoRepository siteNameWithSiteNoRepository;

    @Autowired
    private ExpensesRepo expensesRepo;

    //save a single Site Name
    public SiteNamesWithSiteNo saveSiteName(SiteNamesWithSiteNo siteNamesWithSiteNo){
        return siteNameWithSiteNoRepository.save(siteNamesWithSiteNo);
    }

    public List<SiteNamesWithSiteNo> getAllSiteNameWithSiteNo(){
        return siteNameWithSiteNoRepository.findAll();
    }

    public SiteNamesWithSiteNo updateSiteNamesWithSiteNo(Long id, SiteNamesWithSiteNo siteNamesWithSiteNo){
        Optional<SiteNamesWithSiteNo> existingSiteData = siteNameWithSiteNoRepository.findById(id);

        if (existingSiteData.isPresent()){
            SiteNamesWithSiteNo updatedSiteNames = existingSiteData.get();

            String oldSiteName = updatedSiteNames.getSiteName();  // Store the old site name

            updatedSiteNames.setSiteName(siteNamesWithSiteNo.getSiteName());
            updatedSiteNames.setSiteNo(siteNamesWithSiteNo.getSiteNo());
            SiteNamesWithSiteNo saved = siteNameWithSiteNoRepository.save(updatedSiteNames);

            // 🔄 Update all ExpensesForm entries where siteName == oldSiteName
            List<ExpensesForm> expensesWithOldSiteName = expensesRepo.findBySiteName(oldSiteName);
            for (ExpensesForm expense : expensesWithOldSiteName) {
                expense.setSiteName(siteNamesWithSiteNo.getSiteName());
            }
            expensesRepo.saveAll(expensesWithOldSiteName);

            return saved;
        } else {
            throw new RuntimeException("Site Name With Site No not found: " + id );
        }
    }

    public String uploadSiteNameData(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty. Please upload a valid SQL file.";
        }
        List<SiteNamesWithSiteNo> siteNamesWithSiteNos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;
            if (filename != null && filename.endsWith(".sql")) {
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `site_name_with_site_no`")) {
                        // Extract column names
                        int startIdx = line.indexOf('(') + 1;
                        int endIdx = line.indexOf(')');
                        String columnsPart = line.substring(startIdx, endIdx).replace("`", "").trim();
                        String[] columns = columnsPart.split(",");

                        // Extract values part
                        String valuesPart = line.substring(line.indexOf("VALUES") + 6).trim();
                        valuesPart = valuesPart.substring(1, valuesPart.length() - 1);

                        if (valuesPart.endsWith(")")) {
                            valuesPart = valuesPart.substring(0, valuesPart.length() - 1);
                        }

                        String[] records = valuesPart.split("\\),\\s*\\(");
                        for (String record : records) {
                            String[] fields = record.replace("'", "").split(",");
                            Map<String, String> dataMap = new HashMap<>();

                            for (int i = 0; i < columns.length && i < fields.length; i++) {
                                dataMap.put(columns[i].trim(), fields[i].trim());
                            }

                            // Check if both site_name and site_no are present
                            if (dataMap.containsKey("site_name") && dataMap.containsKey("site_no")) {
                                SiteNamesWithSiteNo siteNamesWithSiteNo = new SiteNamesWithSiteNo();
                                siteNamesWithSiteNo.setSiteName(dataMap.get("site_name"));
                                siteNamesWithSiteNo.setSiteNo(dataMap.get("site_no"));
                                siteNamesWithSiteNos.add(siteNamesWithSiteNo);
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type";
            }
            if (siteNamesWithSiteNos.isEmpty()) {
                return "No valid records found in the file.";
            }
            siteNameWithSiteNoRepository.saveAll(siteNamesWithSiteNos);
            return "File uploaded successfully! " + siteNamesWithSiteNos.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
    @Transactional
    public void deleteAllSiteNames(){
        siteNameWithSiteNoRepository.deleteAll();
    }
    public void deleteSiteNames(Long id){
        siteNameWithSiteNoRepository.deleteById(id);
    }
}
