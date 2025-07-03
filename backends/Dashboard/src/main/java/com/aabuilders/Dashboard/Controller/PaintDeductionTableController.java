package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.PaintDeductionTable;
import com.aabuilders.Dashboard.Service.PaintDeductionTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paintDeduction")
public class PaintDeductionTableController {
    private final PaintDeductionTableService paintDeductionTableService;

    @Autowired
    public PaintDeductionTableController(PaintDeductionTableService paintDeductionTableService){
        this.paintDeductionTableService = paintDeductionTableService;
    }

    @PostMapping("/save")
    public ResponseEntity<List<PaintDeductionTable>> savePaintDeductionTable(@RequestBody List<PaintDeductionTable> paintDeductionTables){
        System.out.println("Received Data:" + paintDeductionTables);
        List<PaintDeductionTable> savedDeduction = paintDeductionTableService.savePaintDeductionTable(paintDeductionTables);
        return ResponseEntity.ok(savedDeduction);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<PaintDeductionTable>> getAllPaintDeductionTable(){
        List<PaintDeductionTable> paintDeductionTables = paintDeductionTableService.getAllPaintDeductionTable();
        return ResponseEntity.ok(paintDeductionTables);
    }
}
