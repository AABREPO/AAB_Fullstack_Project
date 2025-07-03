package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.ContractorNames;
import com.aabuilders.Dashboard.Entity.VendorNames;
import com.aabuilders.Dashboard.Repository.ContractorNamesRepository;
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
public class ContractorNameService {
    @Autowired
    private ContractorNamesRepository contractorNamesRepository;

    public ContractorNames saveContractorName(ContractorNames contractorNames){
        return contractorNamesRepository.save(contractorNames);
    }
    public List<ContractorNames> getAllContractorNames(){
        return contractorNamesRepository.findAll();
    }
    public ContractorNames updateContractorNames(Long id, ContractorNames contractorNames){
        Optional<ContractorNames> existingContractorNames = contractorNamesRepository.findById(id);
        if (existingContractorNames.isPresent()){
            ContractorNames updatedContractorNames = existingContractorNames.get();
            updatedContractorNames.setContractorName(contractorNames.getContractorName());
            return contractorNamesRepository.save(updatedContractorNames);
        }else {
            throw new RuntimeException("Contractor Name not found "+ id );
        }
    }
    public String uploadContractorNameData(MultipartFile file){
        if (file.isEmpty()){
            return "File is empty. please upload a valid SQL file";
        }
        List<ContractorNames> contractorNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            String fileName = file.getOriginalFilename();
            String line;
            if(fileName !=null && fileName.endsWith(".sql")){
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `contractor_names`")){
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
                            if (dataMap.containsKey("contractor_name")) {
                                ContractorNames contractorNames1 = new ContractorNames();
                                contractorNames1.setContractorName(dataMap.get("contractor_name"));
                                contractorNames.add(contractorNames1);
                            }
                        }
                    }
                }
            }else {
                return "Unsupported file type";
            }
            if (contractorNames.isEmpty()){
                return "No valid";
            }
            contractorNamesRepository.saveAll(contractorNames);
            return "File uploaded successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed to upload file" + e.getMessage();
        }
    }
    @Transactional
    public void deleteAllContractorNames(){
        contractorNamesRepository.deleteAll();
    }
    public void deleteContractorName(Long id){
        contractorNamesRepository.deleteById(id);
    }
}
