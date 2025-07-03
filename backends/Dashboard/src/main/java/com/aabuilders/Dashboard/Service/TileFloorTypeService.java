package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.PaintType;
import com.aabuilders.Dashboard.Entity.TileFloorType;
import com.aabuilders.Dashboard.Repository.TileFloorTypeRepository;
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
public class TileFloorTypeService {
    @Autowired
    private TileFloorTypeRepository tileFloorTypeRepository;

    public TileFloorType saveTileFloorType(TileFloorType tileFloorType){
        return tileFloorTypeRepository.save(tileFloorType);
    }
    public List<TileFloorType> getAllTileFloorType(){ return tileFloorTypeRepository.findAll();}

    public TileFloorType updateTileFloorType(Long id, TileFloorType tileFloorType) {
        Optional<TileFloorType> existingTileFloorType = tileFloorTypeRepository.findById(id);
        if (existingTileFloorType.isPresent()) {
            TileFloorType updatedTileFloorType = existingTileFloorType.get();
            updatedTileFloorType.setFloorType(tileFloorType.getFloorType());
            updatedTileFloorType.setFormula(tileFloorType.getFormula());
            return tileFloorTypeRepository.save(updatedTileFloorType);
        }
        return null; // or throw an exception if not found
    }
    public void deleteTileFloorType(Long id) {
        if (tileFloorTypeRepository.existsById(id)) {
            tileFloorTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("TileFloorType with id " + id + " not found");
        }
    }

    public String uploadTileFloorTypes(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file!";
        }

        List<TileFloorType> tileFloorTypes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;

            if (filename != null && filename.endsWith(".csv")) {
                // Handle CSV file
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length >=1) {
                        TileFloorType tileFloorType = new TileFloorType();
                        tileFloorType.setFloorType(values[0].trim());
                        tileFloorType.setFormula(values[1].trim());
                        tileFloorTypes.add(tileFloorType);
                    }
                }
            }else  if (filename != null && filename.endsWith(".sql")) {
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `tile_floor_type`")) {

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
                            if (fields.length >= 1) {
                                TileFloorType tileFloorType = new TileFloorType();
                                tileFloorType.setFloorType(fields[0].trim());
                                tileFloorType.setFormula(fields[1].trim());
                                tileFloorTypes.add(tileFloorType);
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }
            // Save all parsed entries to the database
            tileFloorTypeRepository.saveAll(tileFloorTypes);
            return "File uploaded successfully! " + tileFloorTypes.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
    public void deleteAllTileFloorTypes() {
        tileFloorTypeRepository.deleteAll();
    }

}
