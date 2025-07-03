package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.BathBrandNames;
import com.aabuilders.Dashboard.Entity.BathItemNames;
import com.aabuilders.Dashboard.Repository.BathBrandNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class BathBrandNameService {
    @Autowired
    private BathBrandNameRepository bathBrandNameRepository;
    public BathBrandNames saveBathBrandNames(BathBrandNames bathBrandNames){
        return bathBrandNameRepository.save(bathBrandNames);
    }
    public List<BathBrandNames> getAllBathBrandNames(){
        return bathBrandNameRepository.findAll();
    }
    public BathBrandNames updateBathBrands(Long id, BathBrandNames bathBrandNames){
        return bathBrandNameRepository.findById(id)
                .map(existingBrandName->{
                    existingBrandName.setBrandName(bathBrandNames.getBrandName());
                    return bathBrandNameRepository.save(existingBrandName);
                })
                .orElseThrow(()-> new IllegalArgumentException("Bath Brand Name With id"+id + "not found"));
    }
    public void deleteBathBrandName(Long id){
        if(bathBrandNameRepository.existsById(id)){
            bathBrandNameRepository.deleteById(id);
        }else {
            throw new RuntimeException("Bath Brand Name With id"+ id + "not found");
        }
    }
    public void deleteAllBrandNames(){
        bathBrandNameRepository.deleteAll();
    }
    public String uploadBrandNames(MultipartFile file){
        if (file.isEmpty()){
            return "File is Empty!!";
        }
        List<BathBrandNames> bathBrandNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;
            if(filename != null && filename.endsWith(".csv")){
                while ((line = reader.readLine()) != null){
                    String[] values = line.split(",");
                    if (values.length > 0){
                        BathBrandNames bathBrandNames1 = new BathBrandNames();
                        bathBrandNames1.setBrandName(values[0].trim());
                        bathBrandNames.add(bathBrandNames1);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")){
                while ((line = reader.readLine()) !=null){
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `bath_item_names`")){
                        int startIndex = line.indexOf("('") + 2;
                        int endIndex = line.indexOf("')", startIndex);

                        if (startIndex >1 && endIndex > startIndex){
                            String itemName = line.substring(startIndex, endIndex).trim();
                            BathBrandNames bathBrandNames1 = new BathBrandNames();
                            bathBrandNames1.setBrandName(itemName);
                            bathBrandNames.add(bathBrandNames1);
                        }
                    }
                }
            }else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }
            bathBrandNameRepository.saveAll(bathBrandNames);
            return "File uploaded successfully!";
        }
        catch (Exception e){
            e.printStackTrace();
            return "Failed to upload: " + e.getMessage();
        }
    }
}
