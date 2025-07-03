package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Model.BathFixtureCalculation;
import com.aabuilders.Dashboard.Model.BathFixtureFloorCalculation;
import com.aabuilders.Dashboard.Model.BathFixtureTable;
import com.aabuilders.Dashboard.Repository.BathFixtureCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BathFixtureCalculationService {

    @Autowired
    private BathFixtureCalculationRepository bathFixtureCalculationRepository;
    //save Bath Fixtures
    public BathFixtureCalculation saveBathFixtureCalculation(BathFixtureCalculation bathFixtureCalculation) {
        if ("dumyFile".equalsIgnoreCase(bathFixtureCalculation.getFileName())) {
            return bathFixtureCalculationRepository
                    .findByClientNameAndFileName(bathFixtureCalculation.getClientName(), "dumyFile")
                    .map(existing -> {
                        existing.setDate(bathFixtureCalculation.getDate());
                        existing.setENo(bathFixtureCalculation.getENo());

                        // 🚨 Clear old child records first
                        existing.getBathFixtureCalculations().clear();

                        // ✅ Add new ones
                        existing.getBathFixtureCalculations().addAll(bathFixtureCalculation.getBathFixtureCalculations());

                        return bathFixtureCalculationRepository.save(existing);
                    })
                    .orElseGet(() -> bathFixtureCalculationRepository.save(bathFixtureCalculation));
        } else {
            return bathFixtureCalculationRepository.save(bathFixtureCalculation);
        }
    }

    public List<BathFixtureCalculation> getAllBathFixtureCalculation(){
        return bathFixtureCalculationRepository.findAll();
    }
    public BathFixtureCalculation editBathFixtureCalculation(Long id, BathFixtureCalculation updatedBathFixtureCalculation){
        Optional<BathFixtureCalculation> existingCalculation = bathFixtureCalculationRepository.findById(id);
        if (existingCalculation.isPresent()){
            BathFixtureCalculation calculation = existingCalculation.get();
            calculation.setClientName(updatedBathFixtureCalculation.getClientName());
            calculation.setFileName(updatedBathFixtureCalculation.getFileName());
            calculation.setDate(updatedBathFixtureCalculation.getDate());
            return bathFixtureCalculationRepository.save(calculation);
        }
        throw new RuntimeException("Bath Fixture with ID" + id + "not found");
    }
    public String deleteBathFixture(Long id){
        if (bathFixtureCalculationRepository.existsById(id)){
            bathFixtureCalculationRepository.deleteById(id);
            return "deleted!!!!";
        }
        throw new RuntimeException("not deleted!!!");
    }
    public String deleteAllBathFixture(){
        bathFixtureCalculationRepository.deleteAll();
        return "all data deleted!!!!";
    }

}
