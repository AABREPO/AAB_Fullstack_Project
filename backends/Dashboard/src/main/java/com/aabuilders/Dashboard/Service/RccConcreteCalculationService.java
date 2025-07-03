package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Model.RccConcreteCalculation;
import com.aabuilders.Dashboard.Repository.RccConcreteCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RccConcreteCalculationService {
    @Autowired
    private RccConcreteCalculationRepository rccConcreteCalculationRepository;
    public RccConcreteCalculation saveRccConcreteCalculation(RccConcreteCalculation rccConcreteCalculation){
        return rccConcreteCalculationRepository.save(rccConcreteCalculation);
    }

    public List<RccConcreteCalculation> getAllRccConcreteCalculation(){
        return rccConcreteCalculationRepository.findAll();
    }
}
