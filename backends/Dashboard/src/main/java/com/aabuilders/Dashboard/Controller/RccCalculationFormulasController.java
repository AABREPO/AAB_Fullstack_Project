package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.RccCalculationFormulas;
import com.aabuilders.Dashboard.Service.RccCalculationFormulasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/api/rcc/formulas")
public class RccCalculationFormulasController {
    @Autowired
    private RccCalculationFormulasService rccCalculationFormulasService;

    @PostMapping("/save")
    public ResponseEntity<RccCalculationFormulas> saveCalculationFormulas(@RequestBody RccCalculationFormulas calculationFormulas) {
        return ResponseEntity.ok(rccCalculationFormulasService.saveRccCalculationFormulas(calculationFormulas));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RccCalculationFormulas>> getAllCalculationFormulas() {
        return ResponseEntity.ok(rccCalculationFormulasService.getAllRccCalculationFormulas());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RccCalculationFormulas> updateCalculationFormulas(@PathVariable Long id, @RequestBody RccCalculationFormulas calculationFormulas) {
        return ResponseEntity.ok(rccCalculationFormulasService.updateRccCalculationFormulas(id, calculationFormulas));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRccCalculationFormulas(@PathVariable Long id) {
        rccCalculationFormulasService.deleteRccCalculationFormulas(id);
        return ResponseEntity.ok("Calculation Formulas with id " + id + " has been deleted successfully.");
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllRccCalculationFormulas() {
        rccCalculationFormulasService.deleteAllRccCalculationFormulas();
        return ResponseEntity.ok("All Calculation Formulas records have been deleted successfully!");
    }

    @PostMapping("/bulk_upload")
    public ResponseEntity<String> uploadRccCalculationFormulas(@RequestParam("file") MultipartFile file) {
        try {
            String response = rccCalculationFormulasService.uploadRccCalculationFormulas(file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process the file: " + e.getMessage());
        }
    }
}
