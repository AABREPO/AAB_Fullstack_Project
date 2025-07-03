package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.BathModelWithDetails;
import com.aabuilders.Dashboard.Repository.BathModelWithDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BathModelWithDetailsService {
    @Autowired
    private BathModelWithDetailsRepository bathModelWithDetailsRepository;

    public BathModelWithDetails saveBathModelDetails(String brandName, String itemName, String model, String type, String price, byte[] image, byte[] technicalImage){
        BathModelWithDetails bathModelWithDetails = new BathModelWithDetails();
        bathModelWithDetails.setBrandName(brandName);
        bathModelWithDetails.setItemName(itemName);
        bathModelWithDetails.setModel(model);
        bathModelWithDetails.setType(type);
        bathModelWithDetails.setPrice(price);
        bathModelWithDetails.setImage(image);
        bathModelWithDetails.setTechnicalImage(technicalImage);
        return bathModelWithDetailsRepository.save(bathModelWithDetails);
    }

    public List<BathModelWithDetails> getAllBathWithDetails(){
        return bathModelWithDetailsRepository.findAll();
    }

    public BathModelWithDetails updateBathModelWithDetails(Long id, String brandName, String itemName, String model, String type, String price, byte[] image, byte[] technicalImage){
        BathModelWithDetails bathModelWithDetails = bathModelWithDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bath Model Not found"));
        bathModelWithDetails.setBrandName(brandName);
        bathModelWithDetails.setItemName(itemName);
        bathModelWithDetails.setType(type);
        bathModelWithDetails.setModel(model);
        bathModelWithDetails.setPrice(price);
        if (image != null){
            bathModelWithDetails.setImage(image);
        }
        if(technicalImage !=null){
            bathModelWithDetails.setTechnicalImage(technicalImage);
        }
        return bathModelWithDetailsRepository.save(bathModelWithDetails);
    }
    public void deleteBathModelWithDetails(Long id){
        bathModelWithDetailsRepository.deleteById(id);
    }
    public void deleteAllBathModelWithDetails(){
        bathModelWithDetailsRepository.deleteAll();
    }
    public void saveAll (List<BathModelWithDetails> bathModelWithDetails){
        bathModelWithDetailsRepository.saveAll(bathModelWithDetails);
    }
}
