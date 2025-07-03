package com.example.Dashboard2.Service;

import com.example.Dashboard2.Entity.POTypeColorList;
import com.example.Dashboard2.Repository.POTypeColorListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class POTypeColorListService {

    @Autowired
    private POTypeColorListRepository poTypeColorListRepository;

    public POTypeColorList savePoTypeColorList(POTypeColorList poTypeColorList){
        return poTypeColorListRepository.save(poTypeColorList);
    }

    public List<POTypeColorList> getAllPoTypeColorList(){
        return poTypeColorListRepository.findAll();
    }
    public POTypeColorList updatePOTypeColorList(Long id, POTypeColorList poTypeColorList){
        Optional<POTypeColorList> existingPOTypeColorList = poTypeColorListRepository.findById(id);
        if (existingPOTypeColorList.isPresent()){
            POTypeColorList updatedPoTypeColorList = existingPOTypeColorList.get();
            updatedPoTypeColorList.setTypeColor(poTypeColorList.getTypeColor());
            return poTypeColorListRepository.save(updatedPoTypeColorList);
        } else {
            throw new RuntimeException("Type / Color not found"+ id);
        }
    }
    public void deletePoTypeColor(Long id){
        if (poTypeColorListRepository.existsById(id)){
            poTypeColorListRepository.deleteById(id);
        } else {
            throw new RuntimeException("Type / Color not found" + id);
        }
    }
    public void deleteAllPoTypeColorList(){
        poTypeColorListRepository.deleteAll();
    }
}
