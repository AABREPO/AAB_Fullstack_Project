package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.RccBeamName;
import com.aabuilders.Dashboard.Repository.RccBeamNameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RccBeamNameService {

    @Autowired
    private RccBeamNameRepository rccBeamNameRepository;

    public RccBeamName saveRccBeamName(String beamName, String formula, String rate, byte[] measurementImage){
        RccBeamName beamName1 = new RccBeamName();
        beamName1.setBeamName(beamName);
        beamName1.setFormula(formula);
        beamName1.setRate(rate);
        beamName1.setMeasurementImage(measurementImage);
        return rccBeamNameRepository.save(beamName1);
    }

    public List<RccBeamName>getAllBeamNames(){return rccBeamNameRepository.findAll();}

    public void updatedBeamName(Long id, String beamName, String formula, String rate, byte[] imageData) {
        Optional<RccBeamName> optionalBeamName = rccBeamNameRepository.findById(id);
        if (optionalBeamName.isPresent()) {
            RccBeamName beamNameEntity = optionalBeamName.get();
            beamNameEntity.setBeamName(beamName);
            beamNameEntity.setFormula(formula);
            beamNameEntity.setRate(rate);
            if (imageData != null) {
                beamNameEntity.setMeasurementImage(imageData);
            }
            rccBeamNameRepository.save(beamNameEntity);
        } else {
            throw new EntityNotFoundException("Rcc Beam Name not found for id: " + id);
        }
    }


    public void deleteRccBeamName(Long id){
        rccBeamNameRepository.deleteById(id);
    }
    public void deleteAllRccBeamNames(){
        rccBeamNameRepository.deleteAll();
    }
    public void saveAll(List<RccBeamName> beamNames){
        rccBeamNameRepository.saveAll(beamNames);
    }
}
