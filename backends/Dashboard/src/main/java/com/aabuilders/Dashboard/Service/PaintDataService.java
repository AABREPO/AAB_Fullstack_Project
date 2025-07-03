package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.DTO.PaintDataDTO;
import com.aabuilders.Dashboard.Entity.PaintData;
import com.aabuilders.Dashboard.Repository.PaintDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; // Add this import

import java.util.ArrayList;
import java.util.List;

@Service // Add this annotation to make it a Spring service
public class PaintDataService {

    @Autowired
    private PaintDataRepository paintDataRepository;

    public void savePaintData(List<PaintDataDTO> paintData) {
        for (PaintDataDTO data : paintData) {
            PaintData paintDataEntity = new PaintData();
            paintDataEntity.setClientName(data.getClientName());
            paintDataEntity.setDate(data.getDate());
            paintDataEntity.setFileName(data.getFileName());
            paintDataEntity.setPaintType(data.getPaintType());
            paintDataEntity.setHeight(data.getHeight());
            paintDataEntity.setDeductionArea(data.getDeductionArea());
            paintDataEntity.setArea(data.getArea());
            paintDataEntity.setWastage(data.getWastage());
            paintDataEntity.setPaintName(data.getPaintName());
            paintDataEntity.setPaintColor(data.getPaintColor());
            paintDataEntity.setOrderQty(data.getOrderQty());
            paintDataRepository.save(paintDataEntity);
        }
    }
    public List<PaintDataDTO> getAllPaintData() {
        List<PaintDataDTO> paintDataDTOList = new ArrayList<>();
        List<PaintData> paintDataList = paintDataRepository.findAll(); // Fetch all PaintData entities from the repository

        // Convert PaintData to PaintDataDTO
        for (PaintData paintData : paintDataList) {
            PaintDataDTO dto = new PaintDataDTO();
            dto.setClientName(paintData.getClientName());
            dto.setDate(paintData.getDate());
            dto.setFileName(paintData.getFileName());
            dto.setPaintType(paintData.getPaintType());
            dto.setHeight(paintData.getHeight());
            dto.setDeductionArea(paintData.getDeductionArea());
            dto.setArea(paintData.getArea());
            dto.setWastage(paintData.getWastage());
            dto.setPaintName(paintData.getPaintName());
            dto.setPaintColor(paintData.getPaintColor());
            dto.setOrderQty(paintData.getOrderQty());
            paintDataDTOList.add(dto); // Add DTO to the list
        }
        return paintDataDTOList; // Return the list of DTOs
    }

}