package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.BathItemNames;
import com.aabuilders.Dashboard.Repository.BathItemNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BathItemNameService {
    @Autowired
    private BathItemNamesRepository bathItemNamesRepository;

    public BathItemNames saveBathItemNames(BathItemNames bathItemNames){
        return bathItemNamesRepository.save(bathItemNames);
    }
    public List<BathItemNames> getAllBathItemNames(){
        return bathItemNamesRepository.findAll();
    }
    public BathItemNames updateBathItemNames(Long id, BathItemNames bathItemNames){
        return bathItemNamesRepository.findById(id)
                .map( existingItemName->{
                    existingItemName.setItemName(bathItemNames.getItemName());
                    return bathItemNamesRepository.save(existingItemName);
                })
                .orElseThrow(()-> new IllegalArgumentException("Bath Item Name With id" + id + "not found"));
    }
    public String uploadBathItemNames(MultipartFile file){
        if (file.isEmpty()){
            return "File is empty!!";
        }
        List<BathItemNames> bathItemNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            String filename = file.getOriginalFilename();
            String line;
            if(filename != null && filename.endsWith(".csv")){
                while ((line = reader.readLine()) != null){
                    String[] values = line.split(",");
                    if (values.length > 0){
                        BathItemNames bathItemNames1 = new BathItemNames();
                        bathItemNames1.setItemName(values[0].trim());
                        bathItemNames.add(bathItemNames1);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                while ((line = reader.readLine()) !=null){
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `bath_item_names`")){
                        int startIndex = line.indexOf("('") + 2;
                        int endIndex = line.indexOf("')", startIndex);

                        if (startIndex >1 && endIndex > startIndex){
                            String itemName = line.substring(startIndex, endIndex).trim();
                            BathItemNames bathItemNames1 = new BathItemNames();
                            bathItemNames1.setItemName(itemName);
                            bathItemNames.add(bathItemNames1);
                        }
                    }
                }
            }else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }
            bathItemNamesRepository.saveAll(bathItemNames);
            return "File uploaded successfully!";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed to upload: " + e.getMessage();
        }
    }
    public void deleteBathItemName(Long id){
        if (bathItemNamesRepository.existsById(id)){
            bathItemNamesRepository.deleteById(id);
        } else {
            throw new RuntimeException(" Bath Item Name with id" + id + "not found");
        }
    }
    public void deleteAllBathItemNames(){
        bathItemNamesRepository.deleteAll();
    }
}
