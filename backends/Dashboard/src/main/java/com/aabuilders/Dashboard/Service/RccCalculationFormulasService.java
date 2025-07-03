package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.CalculationFormulas;
import com.aabuilders.Dashboard.Entity.RccCalculationFormulas;
import com.aabuilders.Dashboard.Repository.CalculationFormulasRepository;
import com.aabuilders.Dashboard.Repository.RccCalculationFormulasRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Service
public class RccCalculationFormulasService {
    @Autowired
    private RccCalculationFormulasRepository rccCalculationFormulasRepository;

    public RccCalculationFormulas saveRccCalculationFormulas(RccCalculationFormulas rccCalculationFormulas) {
        return rccCalculationFormulasRepository.save(rccCalculationFormulas);
    }

    public List<RccCalculationFormulas> getAllRccCalculationFormulas() {
        return rccCalculationFormulasRepository.findAll();
    }

    public RccCalculationFormulas updateRccCalculationFormulas(Long id, RccCalculationFormulas rccCalculationFormulas) {
        return rccCalculationFormulasRepository.findById(id)
                .map(existingFormula -> {
                    existingFormula.setFormulas(rccCalculationFormulas.getFormulas());
                    return rccCalculationFormulasRepository.save(existingFormula);
                })
                .orElseThrow(() -> new IllegalArgumentException("Calculation Formulas with id " + id + " not found"));
    }

    public String uploadRccCalculationFormulas(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty. Please upload a valid SQL file.";
        }

        List<RccCalculationFormulas> rccCalculationFormulas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;

            if (filename != null && filename.endsWith(".sql")) {
                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    // Check if the line starts with the correct INSERT INTO statement
                    if (line.startsWith("INSERT INTO `calculation_formulas`") || line.startsWith("INSERT INTO calculation_formulas")) {

                        // Extract the `VALUES` part
                        int valuesIndex = line.indexOf("VALUES");
                        if (valuesIndex != -1) {
                            String valuesPart = line.substring(valuesIndex + 6).trim();

                            // Remove surrounding parentheses from the VALUES part
                            if (valuesPart.startsWith("(") && valuesPart.endsWith(");")) {
                                valuesPart = valuesPart.substring(1, valuesPart.length() - 2).trim();
                            } else if (valuesPart.startsWith("(") && valuesPart.endsWith(")")) {
                                valuesPart = valuesPart.substring(1, valuesPart.length() - 1).trim();
                            }

                            // Split individual records if multiple VALUES are present
                            String[] records = valuesPart.split("\\),\\s*\\(");

                            for (String record : records) {
                                // Extract and clean the formula string
                                String formula = record.replace("'", "").trim();

                                if (!formula.isEmpty()) {
                                    // Add to the list of formulas
                                    RccCalculationFormulas rccCalculationFormula = new RccCalculationFormulas();
                                    rccCalculationFormula.setFormulas(formula);
                                    rccCalculationFormulas.add(rccCalculationFormula);
                                }
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a valid SQL file.";
            }

            if (rccCalculationFormulas.isEmpty()) {
                return "No valid records found in the file. Ensure the file contains valid INSERT statements for the `calculation_formulas` table.";
            }

            // Save valid records to the repository
            rccCalculationFormulasRepository.saveAll(rccCalculationFormulas);
            return "File uploaded successfully! " + rccCalculationFormulas.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
    public void deleteRccCalculationFormulas(Long id) {
        if (rccCalculationFormulasRepository.existsById(id)) {
            rccCalculationFormulasRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Calculation Formulas with id " + id + " not found");
        }
    }

    @Transactional
    public List<RccCalculationFormulas> saveAll(List<RccCalculationFormulas> rccCalculationFormulas) {
        return rccCalculationFormulasRepository.saveAll(rccCalculationFormulas);
    }

    @Transactional
    public void deleteAllRccCalculationFormulas() {
        rccCalculationFormulasRepository.deleteAll();
    }
}
