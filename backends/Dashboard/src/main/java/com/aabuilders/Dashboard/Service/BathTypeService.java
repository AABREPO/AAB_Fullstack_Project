package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.BathItemNames;
import com.aabuilders.Dashboard.Entity.BathType;
import com.aabuilders.Dashboard.Repository.BathItemNamesRepository;
import com.aabuilders.Dashboard.Repository.BathTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BathTypeService {
    @Autowired
    private BathTypeRepository bathTypeRepository;

    public BathType saveBathType(BathType bathType){
        return bathTypeRepository.save(bathType);
    }
    public List<BathType> getAllBathType(){
        return bathTypeRepository.findAll();
    }
    public BathType updateBathType(Long id, BathType bathType){
        return bathTypeRepository.findById(id)
                .map( existingBathType->{
                    existingBathType.setType(bathType.getType());
                    return bathTypeRepository.save(existingBathType);
                })
                .orElseThrow(()-> new IllegalArgumentException("Bath Item Name With id" + id + "not found"));
    }
    public String uploadBathTypes(MultipartFile file){
        if (file.isEmpty()){
            return "File is empty!!";
        }
        List<BathType> bathTypes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            String filename = file.getOriginalFilename();
            String line;
            if(filename != null && filename.endsWith(".csv")){
                while ((line = reader.readLine()) != null){
                    String[] values = line.split(",");
                    if (values.length > 0){
                        BathType bathType = new BathType();
                        bathType.setType(values[0].trim());
                        bathTypes.add(bathType);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                while ((line = reader.readLine()) !=null){
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `bath_item_names`")){
                        int startIndex = line.indexOf("('") + 2;
                        int endIndex = line.indexOf("')", startIndex);

                        if (startIndex >1 && endIndex > startIndex){
                            String bathType = line.substring(startIndex, endIndex).trim();
                            BathType bathType1 = new BathType();
                            bathType1.setType(bathType);
                            bathTypes.add(bathType1);
                        }
                    }
                }
            }else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }
            bathTypeRepository.saveAll(bathTypes);
            return "File uploaded successfully!";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed to upload: " + e.getMessage();
        }
    }
    public void deleteBathType(Long id){
        if (bathTypeRepository.existsById(id)){
            bathTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException(" Bath Item Name with id" + id + "not found");
        }
    }
    public void deleteAllBathType(){
        bathTypeRepository.deleteAll();
    }
}
