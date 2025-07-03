package com.example.Dashboard2.Service;

import com.example.Dashboard2.Entity.POModelList;
import com.example.Dashboard2.Repository.POModelListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class POModelListService {

    @Autowired
    private POModelListRepository poModelListRepository;

    public POModelList savePoModelList(POModelList poModelList){
        return poModelListRepository.save(poModelList);
    }
    public List<POModelList> getAllPOModelList(){
        return poModelListRepository.findAll();
    }

    public POModelList updatePOModelList(Long id, POModelList poModelList){
        Optional<POModelList> existingPoModelList = poModelListRepository.findById(id);
        if (existingPoModelList.isPresent()){
            POModelList updatedPoModelList = existingPoModelList.get();
            updatedPoModelList.setModel(poModelList.getModel());
            return poModelListRepository.save(updatedPoModelList);
        } else {
            throw new RuntimeException("Model not found" + id);
        }
    }
    public void deletePoModel(Long id){
        if (poModelListRepository.existsById(id)){
            poModelListRepository.deleteById(id);
        } else {
            throw new RuntimeException("Model not found" + id);
        }
    }
    public void deleteAllPoModelList(){
        poModelListRepository.deleteAll();
    }
}
