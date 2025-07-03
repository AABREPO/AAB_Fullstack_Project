package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.CalculationFormulas;
import com.aabuilders.Dashboard.Service.CalculationFormulasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/formulas")
public class CalculationFormulasController {

    @Autowired
    private CalculationFormulasService calculationFormulasService;

    @PostMapping("/save")
    public ResponseEntity<CalculationFormulas> saveCalculationFormulas(@RequestBody CalculationFormulas calculationFormulas) {
        return ResponseEntity.ok(calculationFormulasService.saveCalculationFormulas(calculationFormulas));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CalculationFormulas>> getAllCalculationFormulas() {
        return ResponseEntity.ok(calculationFormulasService.getAllCalculationFormulas());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CalculationFormulas> updateCalculationFormulas(@PathVariable Long id, @RequestBody CalculationFormulas calculationFormulas) {
        return ResponseEntity.ok(calculationFormulasService.updateCalculationFormulas(id, calculationFormulas));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCalculationFormulas(@PathVariable Long id) {
        calculationFormulasService.deleteCalculationFormulas(id);
        return ResponseEntity.ok("Calculation Formulas with id " + id + " has been deleted successfully.");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllCalculationFormulas() {
        calculationFormulasService.deleteAllCalculationFormulas();
        return ResponseEntity.ok("All Calculation Formulas records have been deleted successfully!");
    }

    @PostMapping("/bulk_upload")
    public ResponseEntity<String> uploadCalculationFormulas(@RequestParam("file") MultipartFile file) {
        try {
            String response = calculationFormulasService.uploadCalculationFormulas(file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process the file: " + e.getMessage());
        }
    }
}
