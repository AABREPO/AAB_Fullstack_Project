package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.DTO.PaintDeductionRequestDTO;
import com.aabuilders.Dashboard.Entity.PaintDeductionEntity;
import com.aabuilders.Dashboard.Service.PaintDeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paint-deductions")
public class PaintDeductionController {

    @Autowired
    private PaintDeductionService paintDeductionService;

    @PostMapping("/save")
    public ResponseEntity<String> savePaintDeductionData(@RequestBody PaintDeductionRequestDTO paintDeductionRequestDTO) {
        try {
            paintDeductionService.savePaintDeductionData(paintDeductionRequestDTO);
            return new ResponseEntity<>("Data saved successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving data: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<PaintDeductionEntity>> getAllPaintDeductionData() {
        try {
            List<PaintDeductionEntity> data = paintDeductionService.getAllPaintDeductionData();
            return new ResponseEntity<>(data, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
