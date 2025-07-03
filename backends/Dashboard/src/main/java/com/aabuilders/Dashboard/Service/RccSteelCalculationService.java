package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Model.RccSteelCalculation;
import com.aabuilders.Dashboard.Repository.RccSteelCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RccSteelCalculationService {
    @Autowired
    private RccSteelCalculationRepository rccSteelCalculationRepository;

    public RccSteelCalculation saveRccSteelCalculation(RccSteelCalculation rccSteelCalculation){
        return rccSteelCalculationRepository.save(rccSteelCalculation);
    }
}
