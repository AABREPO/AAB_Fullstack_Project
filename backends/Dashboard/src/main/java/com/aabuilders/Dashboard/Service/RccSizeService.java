package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.RccSize;
import com.aabuilders.Dashboard.Repository.RccSizeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RccSizeService {

    @Autowired
    private RccSizeRepository rccSizeRepository;

    public RccSize saveRccSize(RccSize rccSize){
        return rccSizeRepository.save(rccSize);
    }
    public List<RccSize> getAllRccSize(){
        return rccSizeRepository.findAll();
    }
    public RccSize updateRccSize(Long id, RccSize rccSize){
        Optional<RccSize> existingRccSize = rccSizeRepository.findById(id);
        if (existingRccSize.isPresent()){
            RccSize updatedRccSize = existingRccSize.get();
            updatedRccSize.setSize(rccSize.getSize());
            return rccSizeRepository.save(updatedRccSize);
        }
        return  null;
    }
    public void deleteRccSize(Long id){
        if(rccSizeRepository.existsById(id)){
            rccSizeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Size With Id "+ id +"not found");
        }
    }
    @Transactional
    public List<RccSize> saveAll(List<RccSize> rccSizes){
        return rccSizeRepository.saveAll(rccSizes);
    }
    @Transactional
    public void deleteAllRccSize(){
        rccSizeRepository.deleteAll();
    }
}
