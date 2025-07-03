package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.CalculationFormulas;
import com.aabuilders.Dashboard.Repository.CalculationFormulasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class CalculationFormulasService {
    @Autowired
    private CalculationFormulasRepository calculationFormulasRepository;
    public CalculationFormulas saveCalculationFormulas(CalculationFormulas calculationFormulas) {
        return calculationFormulasRepository.save(calculationFormulas);
    }
    public List<CalculationFormulas> getAllCalculationFormulas() {
        return calculationFormulasRepository.findAll();
    }
    public CalculationFormulas updateCalculationFormulas(Long id, CalculationFormulas calculationFormulas) {
        return calculationFormulasRepository.findById(id)
                .map(existingFormula -> {
                    existingFormula.setFormulas(calculationFormulas.getFormulas());
                    return calculationFormulasRepository.save(existingFormula);
                })
                .orElseThrow(() -> new IllegalArgumentException("Calculation Formulas with id " + id + " not found"));
    }
    public String uploadCalculationFormulas(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty. Please upload a valid SQL file.";
        }
        List<CalculationFormulas> calculationFormulas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;
            if (filename != null && filename.endsWith(".sql")) {
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `calculation_formulas`") || line.startsWith("INSERT INTO calculation_formulas")) {
                        int valuesIndex = line.indexOf("VALUES");
                        if (valuesIndex != -1) {
                            String valuesPart = line.substring(valuesIndex + 6).trim();
                            if (valuesPart.startsWith("(") && valuesPart.endsWith(");")) {
                                valuesPart = valuesPart.substring(1, valuesPart.length() - 2).trim();
                            } else if (valuesPart.startsWith("(") && valuesPart.endsWith(")")) {
                                valuesPart = valuesPart.substring(1, valuesPart.length() - 1).trim();
                            }
                            String[] records = valuesPart.split("\\),\\s*\\(");
                            for (String record : records) {
                                String formula = record.replace("'", "").trim();
                                if (!formula.isEmpty()) {
                                    CalculationFormulas calculationFormula = new CalculationFormulas();
                                    calculationFormula.setFormulas(formula);
                                    calculationFormulas.add(calculationFormula);
                                }
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a valid SQL file.";
            }
            if (calculationFormulas.isEmpty()) {
                return "No valid records found in the file. Ensure the file contains valid INSERT statements for the `calculation_formulas` table.";
            }
            calculationFormulasRepository.saveAll(calculationFormulas);
            return "File uploaded successfully! " + calculationFormulas.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
    public void deleteCalculationFormulas(Long id) {
        if (calculationFormulasRepository.existsById(id)) {
            calculationFormulasRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Calculation Formulas with id " + id + " not found");
        }
    }
    @Transactional
    public List<CalculationFormulas> saveAll(List<CalculationFormulas> calculationFormulas) {
        return calculationFormulasRepository.saveAll(calculationFormulas);
    }
    @Transactional
    public void deleteAllCalculationFormulas() {
        calculationFormulasRepository.deleteAll();
    }
}
