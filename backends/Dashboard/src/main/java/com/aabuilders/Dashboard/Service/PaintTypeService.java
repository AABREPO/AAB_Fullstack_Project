package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.PaintType;
import com.aabuilders.Dashboard.Repository.PaintTypeRepository;
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
public class PaintTypeService {

    @Autowired
    private PaintTypeRepository paintTypeRepository;

    // Save a single PaintType
    public PaintType savePaintType(PaintType paintType) {
        return paintTypeRepository.save(paintType);
    }

    // Get all PaintTypes
    public List<PaintType> getAllPaintType() {
        return paintTypeRepository.findAll();
    }

    // Update a PaintType by ID
    public PaintType updatePaintType(Long id, PaintType paintType) {
        Optional<PaintType> existingPaintType = paintTypeRepository.findById(id);
        if (existingPaintType.isPresent()) {
            PaintType updatedPaintType = existingPaintType.get();
            updatedPaintType.setPaintItem(paintType.getPaintItem());
            updatedPaintType.setFormulas(paintType.getFormulas()); // Update formulas
            return paintTypeRepository.save(updatedPaintType);
        } else {
            throw new RuntimeException("PaintType with id " + id + " not found");
        }
    }

    // Delete a PaintType by ID
    public void deletePaintType(Long id) {
        if (paintTypeRepository.existsById(id)) {
            paintTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("PaintType with id " + id + " not found");
        }
    }

    // Save multiple PaintTypes at once
    @Transactional
    public List<PaintType> saveAll(List<PaintType> paintTypes) {
        return paintTypeRepository.saveAll(paintTypes);
    }

    // Delete all PaintTypes
    @Transactional
    public void deleteAllPaintType() {
        paintTypeRepository.deleteAll();
    }

    public String uploadPaintTypeData(MultipartFile file) {
        if (file.isEmpty()) {
            return "File is empty. Please upload a valid SQL file.";
        }

        List<PaintType> paintTypes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;

            if (filename != null && filename.endsWith(".sql")) {
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `paint_type`")) {

                        String valuesPart = line.substring(line.indexOf("VALUES") + 6).trim();

                        // Remove the outermost parentheses
                        valuesPart = valuesPart.substring(1, valuesPart.length() - 1);

                        // Remove the last closing parenthesis if it exists at the end
                        if (valuesPart.endsWith(")")) {
                            valuesPart = valuesPart.substring(0, valuesPart.length() - 1);
                        }

                        String[] records = valuesPart.split("\\),\\s*\\(");

                        for (String record : records) {
                            // Remove single quotes and split by commas
                            String[] fields = record.replace("'", "").split(",");
                            if (fields.length >= 2) {
                                PaintType paintType = new PaintType();
                                paintType.setPaintItem(fields[0].trim());
                                paintType.setFormulas(fields[1].trim());
                                paintTypes.add(paintType);
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a valid SQL file.";
            }

            if (paintTypes.isEmpty()) {
                return "No valid records found in the file. Ensure the file contains valid INSERT statements for the `paint_type` table.";
            }

            // Save to the database
            paintTypeRepository.saveAll(paintTypes);
            return "File uploaded successfully! " + paintTypes.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
}