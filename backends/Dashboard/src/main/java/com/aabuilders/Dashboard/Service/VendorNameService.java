package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.ExpensesForm;
import com.aabuilders.Dashboard.Entity.SiteNamesWithSiteNo;
import com.aabuilders.Dashboard.Entity.VendorNames;
import com.aabuilders.Dashboard.Repository.ExpensesRepo;
import com.aabuilders.Dashboard.Repository.VendorNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class VendorNameService {
    @Autowired
    private VendorNameRepository vendorNameRepository;
    @Autowired
    private ExpensesRepo expensesRepo;
    public VendorNames saveVendorName(VendorNames vendorNames){
        return vendorNameRepository.save(vendorNames);
    }
    public List<VendorNames> getAllVendorNames(){
        return vendorNameRepository.findAll();
    }

    public VendorNames updateVendorNames(Long id, VendorNames vendorNames){
        Optional<VendorNames> existingVendorNames = vendorNameRepository.findById(id);
        if (existingVendorNames.isPresent()){
            VendorNames updatedVendorName = existingVendorNames.get();
            String oldVendorName = updatedVendorName.getVendorName();
            updatedVendorName.setVendorName(vendorNames.getVendorName());
            VendorNames saved = vendorNameRepository.save(updatedVendorName);

            // update all ExpensesForm entries where same vendorName == oldVendorName
            List<ExpensesForm> expensesWithOldVendorName = expensesRepo.findByVendor(oldVendorName);
            for (ExpensesForm expense : expensesWithOldVendorName) {
                expense.setVendor(vendorNames.getVendorName());
            }
            expensesRepo.saveAll(expensesWithOldVendorName);
            return saved;
        } else {
            throw new RuntimeException("Vendor Name not found"+ id );
        }
    }
    public String uploadVendorNameData(MultipartFile file){
        if (file.isEmpty()){
            return "File is empty. please upload a valid SQL file";
        }
        List<VendorNames> vendorNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            String fileName = file.getOriginalFilename();
            String line;
            if(fileName !=null && fileName.endsWith(".sql")){
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `vendor_names`")){
                        int startIdx = line.indexOf('(') + 1;
                        int endIdx = line.indexOf(')');
                        String columnsPart = line.substring(startIdx, endIdx).replace("`", "").trim();
                        String[] columns = columnsPart.split(",");
                        String valuesPart = line.substring(line.indexOf("VALUES") + 6).trim();
                        valuesPart = valuesPart.substring(1, valuesPart.length() -1);
                        if (valuesPart.endsWith(")")){
                            valuesPart = valuesPart.substring(0, valuesPart.length() -1);
                        }
                        String[] records = valuesPart.split("\\),\\s*\\(");
                        for (String record : records) {
                            String[] fields = record.replace("'", "").split(",");
                            Map<String, String> dataMap = new HashMap<>();
                            for (int i = 0; i < columns.length && i < fields.length; i++) {
                                dataMap.put(columns[i].trim(), fields[i].trim());
                            }
                            // Check if both site_name and site_no are present
                            if (dataMap.containsKey("vendor_name")) {
                                VendorNames vendorNames1 = new VendorNames();
                                vendorNames1.setVendorName(dataMap.get("vendor_name"));
                                vendorNames.add(vendorNames1);
                            }
                        }
                    }
                }
            }else {
                return "Unsupported file type";
            }
            if (vendorNames.isEmpty()){
                return "No valid";
            }
            vendorNameRepository.saveAll(vendorNames);
            return "File uploaded successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed to upload file" + e.getMessage();
        }
    }
    @Transactional
    public void deleteAllVendorNames(){
        vendorNameRepository.deleteAll();
    }
    public void deleteVendorName(Long id){
        vendorNameRepository.deleteById(id);
    }
}
