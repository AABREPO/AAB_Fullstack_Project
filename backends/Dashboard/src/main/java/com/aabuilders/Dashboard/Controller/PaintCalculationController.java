package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Model.PaintCalculation;
import com.aabuilders.Dashboard.Service.PaintCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paint_calculation")
public class PaintCalculationController {

    @Autowired
    private PaintCalculationService paintCalculationService;

    @PostMapping("/save/paints")
    public PaintCalculation savePaintCalculation(@RequestBody PaintCalculation paintCalculation) {
        return paintCalculationService.savePaintCalculation(paintCalculation);
    }
    @GetMapping("/all/paints")
    public List<PaintCalculation> getAllPaintCalculations() {
        return paintCalculationService.getAllPaintCalculations();
    }

    @PutMapping("/edit/paints/{id}")
    public PaintCalculation editPaintCalculation(@PathVariable Long id, @RequestBody PaintCalculation updatedPaintCalculation) {
        return paintCalculationService.editPaintCalculation(id, updatedPaintCalculation);
    }

    // Delete Paint Calculation by ID
    @DeleteMapping("/delete/paints/{id}")
    public String deletePaintCalculation(@PathVariable Long id) {
        return paintCalculationService.deletePaintCalculation(id);
    }

    // Delete All Paint Calculations
    @DeleteMapping("/delete/all_paints")
    public String deleteAllPaintCalculations() {
        return paintCalculationService.deleteAllPaintCalculations();
    }
}
