package com.example.Dashboard2.Service;

import com.example.Dashboard2.Entity.POBrandList;
import com.example.Dashboard2.Repository.POBrandListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class POBrandListService {

    @Autowired
    private POBrandListRepository poBrandListRepository;

    public POBrandList savePoBrandList(POBrandList poBrandList){
        return poBrandListRepository.save(poBrandList);
    }
    public List<POBrandList> getAllPOBrandList(){
        return poBrandListRepository.findAll();
    }
    public POBrandList updatePOBrandList(Long id, POBrandList poBrandList){
        Optional<POBrandList> existingPOBrandList = poBrandListRepository.findById(id);
        if (existingPOBrandList.isPresent()){
            POBrandList updatedPoBrandList = existingPOBrandList.get();
            updatedPoBrandList.setBrand(poBrandList.getBrand());
            return poBrandListRepository.save(updatedPoBrandList);
        } else {
            throw new RuntimeException("Brand not found" + id);
        }
    }
    public void deletePoBrand(Long id){
        if (poBrandListRepository.existsById(id)){
            poBrandListRepository.deleteById(id);
        } else {
            throw new RuntimeException("Brand not found" + id);
        }
    }
    public void deleteAllPoBrandList(){
        poBrandListRepository.deleteAll();
    }
}
