package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Model.Calculation;
import com.aabuilders.Dashboard.Repository.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CalculationService {
    @Autowired
    private CalculationRepository calculationRepository;

    public Calculation saveCalculation(Calculation calculation) {
        calculation.setFileName(formatFileName(calculation.getFileName()));
        System.out.println("Saving calculation: " + calculation);
        return calculationRepository.save(calculation);
    }

    // Helper method to format fileName
    private String formatFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return fileName; // Return as-is if null or empty
        }
        // Ensure the date part uses dashes instead of slashes
        return fileName.replace("/", "-");
    }

    // Get all calculations
    @Transactional
    public List<Calculation> getAllCalculations() {
        return calculationRepository.findAll();
    }

    // Get calculations by client name (including versioned ones)
    public List<Calculation> getCalculationsByClientName(String clientName) {
        return calculationRepository.findByClientNameStartingWith(clientName);
    }

    // Edit calculation: create new versioned calculation with clientName-Rx
    public Calculation editCalculation(Calculation newCalculation, String clientName) {
        long versionCount = calculationRepository.countByClientNameStartingWith(clientName);
        String versionedClientName = clientName + "-R" + versionCount;
        newCalculation.setClientName(versionedClientName);
        return calculationRepository.save(newCalculation);
    }
    public Calculation updateCalculationFields(Long id, Calculation updatedFields) {
        return calculationRepository.findById(id).map(calculation -> {
            if (updatedFields.getClientName() != null) {
                calculation.setClientName(updatedFields.getClientName());
            }
            if (updatedFields.getDate() != null) {
                calculation.setDate(updatedFields.getDate());
            }
            if (updatedFields.getFileName() != null) {
                calculation.setFileName(formatFileName(updatedFields.getFileName()));
            }
            return calculationRepository.save(calculation);
        }).orElse(null); // Return null if calculation not found
    }


    public boolean deleteCalculation(Long id) {
        if (calculationRepository.existsById(id)) {
            calculationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
