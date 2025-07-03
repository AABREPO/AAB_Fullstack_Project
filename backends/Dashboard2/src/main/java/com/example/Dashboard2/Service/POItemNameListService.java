package com.example.Dashboard2.Service;

import com.example.Dashboard2.Entity.POItemNameList;
import com.example.Dashboard2.Repository.POItemNameListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class POItemNameListService {

    @Autowired
    private POItemNameListRepo poItemNameListRepo;

    public POItemNameList saveAllItemNameList(POItemNameList poItemNameList){
        return poItemNameListRepo.save(poItemNameList);
    }
    public List<POItemNameList> getAllPOItemNameList(){
        return poItemNameListRepo.findAll();
    }

    public POItemNameList editItemName(Long id, POItemNameList updatedPOItemName){
        Optional<POItemNameList> existingPOItemName = poItemNameListRepo.findById(id);
        if (existingPOItemName.isPresent()){
            POItemNameList pOItemName = existingPOItemName.get();
            pOItemName.setItemName(updatedPOItemName.getItemName());
            return poItemNameListRepo.save(pOItemName);
        }
        throw new RuntimeException("PO Item Name With Id not found" + id);
    }

    public String deletePOItemNameList(Long id){
        if (poItemNameListRepo.existsById(id)){
            poItemNameListRepo.deleteById(id);
            return "PO Item Name With Id had deleted"+ id;
        }
        throw new RuntimeException("PO Item Name not found" + id);
    }
    public String deleteAllPOItemNameList(){
        poItemNameListRepo.deleteAll();
        return "All Item Name have been deleted";
    }
}
