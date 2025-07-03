package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.DTO.PaintDeductionRequestDTO;
import com.aabuilders.Dashboard.Entity.PaintDeductionEntity;
import com.aabuilders.Dashboard.Repository.PaintDeductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaintDeductionService {

    @Autowired
    private PaintDeductionRepository paintDeductionRepository;

    public void savePaintDeductionData(PaintDeductionRequestDTO requestDTO) {
        // Convert DTO to Entity
        PaintDeductionEntity entity = new PaintDeductionEntity();
        entity.setClientName(requestDTO.getClientName());
        entity.setFileName(requestDTO.getFileName());
        entity.setDate(requestDTO.getDate());
        entity.setData(requestDTO.getData());

        // Save entity to database
        paintDeductionRepository.save(entity);
    }
    public List<PaintDeductionEntity> getAllPaintDeductionData() {
        return paintDeductionRepository.findAll();
    }
}
