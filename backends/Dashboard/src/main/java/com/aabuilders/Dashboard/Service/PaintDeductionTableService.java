package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.PaintDeductionTable;
import com.aabuilders.Dashboard.Repository.PaintDeductionTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaintDeductionTableService {

    private final PaintDeductionTableRepository paintDeductionTableRepository;
    @Autowired
    public PaintDeductionTableService(PaintDeductionTableRepository paintDeductionTableRepository){
        this.paintDeductionTableRepository = paintDeductionTableRepository;
    }

    public List<PaintDeductionTable> savePaintDeductionTable(List<PaintDeductionTable> paintDeductionTables){
        return paintDeductionTableRepository.saveAll(paintDeductionTables);
    }

    public List<PaintDeductionTable> getAllPaintDeductionTable(){ return paintDeductionTableRepository.findAll();}
}
