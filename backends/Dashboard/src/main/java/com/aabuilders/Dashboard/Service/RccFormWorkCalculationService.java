package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Model.RccFormWorkCalculation;
import com.aabuilders.Dashboard.Repository.RccFormWorkCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RccFormWorkCalculationService {
    @Autowired
    private RccFormWorkCalculationRepository rccFormWorkCalculationRepository;
    public RccFormWorkCalculation saveRccFormWorkCalculation(RccFormWorkCalculation rccFormWorkCalculation){
        return rccFormWorkCalculationRepository.save(rccFormWorkCalculation);
    }

    public List<RccFormWorkCalculation> getAllRccFormWorkCalculation(){return rccFormWorkCalculationRepository.findAll();}
}
