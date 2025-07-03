package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.RccBeamTypes;
import com.aabuilders.Dashboard.Repository.RccBeamTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RccBeamTypesService {
    @Autowired
    private RccBeamTypesRepository rccBeamTypesRepository;

    public RccBeamTypes saveRccBeamTypes(RccBeamTypes rccBeamTypes){
        return rccBeamTypesRepository.save(rccBeamTypes);
    }
    public List<RccBeamTypes> getAllRccBeamTypes(){ return rccBeamTypesRepository.findAll();}

    public RccBeamTypes updateRccBeamTypes(Long id, RccBeamTypes rccBeamTypes) {
        Optional<RccBeamTypes> existingRccBeamTypes = rccBeamTypesRepository.findById(id);

        if (existingRccBeamTypes.isPresent()) {
            RccBeamTypes updatedRccBeamTypes = existingRccBeamTypes.get();
            updatedRccBeamTypes.setBeamType(rccBeamTypes.getBeamType());
            updatedRccBeamTypes.setBeamName(rccBeamTypes.getBeamName());
            return rccBeamTypesRepository.save(updatedRccBeamTypes);
        } else {
            throw new RuntimeException("TileFloorName with id " + id + " not found");
        }
    }
    public void deleteRccBeamTypes(Long id) {
        if (rccBeamTypesRepository.existsById(id)) {
            rccBeamTypesRepository.deleteById(id);
        } else {
            throw new RuntimeException("TileFloorName with id " + id + " not found");
        }
    }

    public void deleteAllRccBeamTypes() {
        rccBeamTypesRepository.deleteAll();
    }
}
