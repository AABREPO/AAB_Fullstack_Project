package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.TileFloorName;
import com.aabuilders.Dashboard.Repository.TileFloorNameRepository;
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
public class TileFloorNameService {
    @Autowired
    private TileFloorNameRepository tileFloorNameRepository;

    public TileFloorName saveTileFloorName(TileFloorName tileFloorName){
        return tileFloorNameRepository.save(tileFloorName);
    }
    public List<TileFloorName> getAllTileFloorName(){ return tileFloorNameRepository.findAll();}

    public TileFloorName updateTileFloorName(Long id, TileFloorName tileFloorName) {
        Optional<TileFloorName> existingTileFloorName = tileFloorNameRepository.findById(id);

        if (existingTileFloorName.isPresent()) {
            TileFloorName updatedTileFloorName = existingTileFloorName.get();
            updatedTileFloorName.setFloorName(tileFloorName.getFloorName()); // Update fields as needed
            return tileFloorNameRepository.save(updatedTileFloorName);
        } else {
            throw new RuntimeException("TileFloorName with id " + id + " not found");
        }
    }
    public void deleteTileFloorName(Long id) {
        if (tileFloorNameRepository.existsById(id)) {
            tileFloorNameRepository.deleteById(id);
        } else {
            throw new RuntimeException("TileFloorName with id " + id + " not found");
        }
    }

    public String uploadTileFloorNames(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file!";
        }

        List<TileFloorName> tileFloorNames = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;

            if (filename != null && filename.endsWith(".csv")) {
                // CSV file handling
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length > 0) {
                        TileFloorName tileFloorName = new TileFloorName();
                        tileFloorName.setFloorName(values[0].trim());
                        tileFloorNames.add(tileFloorName);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                // SQL file handling
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `tile_floor_name`")) {
                        int startIndex = line.indexOf("('") + 2;
                        int endIndex = line.indexOf("')", startIndex);

                        if (startIndex > 1 && endIndex > startIndex) {
                            String floorName = line.substring(startIndex, endIndex).trim();
                            TileFloorName tileFloorName = new TileFloorName();
                            tileFloorName.setFloorName(floorName);
                            tileFloorNames.add(tileFloorName);
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }

            // Save all parsed entries to the database
            tileFloorNameRepository.saveAll(tileFloorNames);
            return "File uploaded successfully! " + tileFloorNames.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
    public void deleteAllTileFloorNames() {
        tileFloorNameRepository.deleteAll();
    }
}
