package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.PaintVariant;
import com.aabuilders.Dashboard.Repository.PaintVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaintVariantService {

    @Autowired
    private PaintVariantRepository paintVariantRepository;

    public PaintVariant savePaintVariant(PaintVariant paintVariant) {
        return paintVariantRepository.save(paintVariant);
    }

    public List<PaintVariant> getAllPaintVariants() {
        return paintVariantRepository.findAll();
    }

    public PaintVariant updatePaintVariant(Long id, PaintVariant paintVariant) {
        return paintVariantRepository.findById(id).map(existingPaintVariant -> {
            existingPaintVariant.setPaintName(paintVariant.getPaintName());
            existingPaintVariant.setPaintType(paintVariant.getPaintType());
            existingPaintVariant.setPaintCoverBySqft(paintVariant.getPaintCoverBySqft());
            return paintVariantRepository.save(existingPaintVariant);
        }).orElse(null); // or throw an exception if not found
    }

    public void deletePaintVariant(Long id) {
        paintVariantRepository.findById(id).ifPresentOrElse(
                paintVariantRepository::delete,
                () -> { throw new RuntimeException("PaintVariant with id " + id + " not found"); }
        );
    }

    public String uploadPaintVariants(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file!";
        }

        List<PaintVariant> paintVariants = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;

            if (filename != null && filename.endsWith(".csv")) {
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 3) {  // Adjusted for 3 fields
                        PaintVariant paintVariant = new PaintVariant();
                        paintVariant.setPaintName(values[0].trim());
                        paintVariant.setPaintType(values[1].trim());
                        paintVariant.setPaintCoverBySqft(values[2].trim());
                        paintVariants.add(paintVariant);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `paint_name_details`")) {
                        int startIndex = line.indexOf("('") + 2;
                        int endIndex = line.lastIndexOf("')");
                        if (startIndex > 1 && endIndex > startIndex) {
                            String valuesStr = line.substring(startIndex, endIndex);
                            String[] values = valuesStr.split("', '");
                            if (values.length == 3) {
                                PaintVariant paintVariant = new PaintVariant();
                                paintVariant.setPaintName(values[0].trim());
                                paintVariant.setPaintType(values[1].trim());
                                paintVariant.setPaintCoverBySqft(values[2].trim());
                                paintVariants.add(paintVariant);
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }
            if (paintVariants.isEmpty()) {
                return "No valid records found in the file.";
            }
            paintVariantRepository.saveAll(paintVariants);
            return "File uploaded successfully! " + paintVariants.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
    public void deleteAllPaintVariants() {
        paintVariantRepository.deleteAll();
    }
}
