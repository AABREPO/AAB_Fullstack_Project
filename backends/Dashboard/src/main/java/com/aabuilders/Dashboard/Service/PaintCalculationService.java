package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Model.PaintCalculation;
import com.aabuilders.Dashboard.Repository.PaintCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaintCalculationService {

    @Autowired
    private PaintCalculationRepository paintCalculationRepository;
    // Save Paint Calculation
    public PaintCalculation savePaintCalculation(PaintCalculation paintCalculation) {
        return paintCalculationRepository.save(paintCalculation);
    }
    // Retrieve all Paint Calculations
    public List<PaintCalculation> getAllPaintCalculations() {
        return paintCalculationRepository.findAll();
    }
    // Edit Paint Calculation by ID
    public PaintCalculation editPaintCalculation(Long id, PaintCalculation updatedPaintCalculation) {
        Optional<PaintCalculation> existingCalculation = paintCalculationRepository.findById(id);
        if (existingCalculation.isPresent()) {
            PaintCalculation calculation = existingCalculation.get();
            calculation.setClientName(updatedPaintCalculation.getClientName());
            calculation.setFileName(updatedPaintCalculation.getFileName());
            calculation.setDate(updatedPaintCalculation.getDate());
            return paintCalculationRepository.save(calculation);
        }
        throw new RuntimeException("PaintCalculation with ID " + id + " not found.");
    }
    // Delete Paint Calculation by ID
    public String deletePaintCalculation(Long id) {
        if (paintCalculationRepository.existsById(id)) {
            paintCalculationRepository.deleteById(id);
            return "PaintCalculation with ID " + id + " has been deleted.";
        }
        throw new RuntimeException("PaintCalculation with ID " + id + " not found.");
    }
    // Delete All Paint Calculations
    public String deleteAllPaintCalculations() {
        paintCalculationRepository.deleteAll();
        return "All PaintCalculations have been deleted.";
    }
}
