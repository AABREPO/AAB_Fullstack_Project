package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.CeilingCoat;
import com.aabuilders.Dashboard.Repository.CeilingCoatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CeilingCoatService {

    private final CeilingCoatRepository ceilingCoatRepository;

    @Autowired
    public CeilingCoatService(CeilingCoatRepository ceilingCoatRepository) {
        this.ceilingCoatRepository = ceilingCoatRepository;
    }

    public List<CeilingCoat> saveCeilingCoats(List<CeilingCoat> ceilingCoats) {
        return ceilingCoatRepository.saveAll(ceilingCoats);
    }

    // New method to fetch all ceiling coats
    public List<CeilingCoat> getAllCeilingCoats() {
        return ceilingCoatRepository.findAll();
    }
}
