package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Model.Calculation;
import com.aabuilders.Dashboard.Service.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tile/tile")
public class CalculationController {
    @Autowired
    private CalculationService calculationService;

    // Save calculation
    @PostMapping("/save")
    public ResponseEntity<Calculation> saveCalculation(@RequestBody Calculation calculation) {
        Calculation savedCalculation = calculationService.saveCalculation(calculation);
        return ResponseEntity.ok(savedCalculation);
    }

    // Get all calculations
    @GetMapping("/all")
    public ResponseEntity<List<Calculation>> getAllCalculations() {
        List<Calculation> calculations = calculationService.getAllCalculations();
        return ResponseEntity.ok(calculations);
    }

    // Get calculations by client name
    @GetMapping("/client/{clientName}")
    public ResponseEntity<List<Calculation>> getCalculationsByClientName(@PathVariable String clientName) {
        List<Calculation> calculations = calculationService.getCalculationsByClientName(clientName);
        return ResponseEntity.ok(calculations);
    }

    // Edit calculation (create a new versioned calculation)
    @PostMapping("/edit/{clientName}")
    public ResponseEntity<Calculation> editCalculation(
            @PathVariable String clientName,
            @RequestBody Calculation newCalculation) {
        Calculation updatedCalculation = calculationService.editCalculation(newCalculation, clientName);
        return ResponseEntity.ok(updatedCalculation);
    }
    // Update specific fields of a calculation
    @PutMapping("/update/{id}")
    public ResponseEntity<Calculation> updateCalculation(
            @PathVariable Long id,
            @RequestBody Calculation updatedFields) {
        Calculation updatedCalculation = calculationService.updateCalculationFields(id, updatedFields);
        if (updatedCalculation != null) {
            return ResponseEntity.ok(updatedCalculation);
        } else {
            return ResponseEntity.status(404).body(null); // Calculation not found
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCalculation(@PathVariable Long id) {
        boolean isDeleted = calculationService.deleteCalculation(id);
        if (isDeleted) {
            return ResponseEntity.ok("Calculation deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Calculation not found.");
        }
    }
}
