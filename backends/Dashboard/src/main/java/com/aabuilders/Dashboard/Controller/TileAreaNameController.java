package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.TileAreaName;
import com.aabuilders.Dashboard.Service.TileAreaNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tile")
public class TileAreaNameController {

    @Autowired
    private TileAreaNameService tileAreaNameService;
    @PostMapping("/nameArea")
    public TileAreaName saveTileAreaName(@RequestBody TileAreaName tileAreaName) {
        return tileAreaNameService.saveTileAreaName(tileAreaName);
    }

    @GetMapping("/areaName")
    public List<TileAreaName> getAllTileAreaNames() {
        return tileAreaNameService.getAllTileAreaNames();
    }

    @PutMapping("/nameArea/{id}")
    public TileAreaName updateTileAreaName(@PathVariable Long id, @RequestBody TileAreaName tileAreaName) {
        return tileAreaNameService.updateTileAreaName(id, tileAreaName);
    }
    @DeleteMapping("/nameArea/{id}")
    public void deleteTileAreaName(@PathVariable Long id) {
        tileAreaNameService.deleteTileAreaName(id);
    }
    @DeleteMapping("/nameArea/all")
    public String deleteAllTileAreaNames() {
        tileAreaNameService.deleteAllTileAreaNames();
        return "All TileAreaName records have been deleted successfully!";
    }
    @PostMapping("/bulkUpload")
    public String uploadTileAreaNames(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file!";
        }
        List<TileAreaName> tileAreaNames = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            if (filename != null && filename.endsWith(".csv")) {
                // Handle CSV file
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length > 0) {
                        TileAreaName tileAreaName = new TileAreaName();
                        tileAreaName.setAreaName(values[0].trim());
                        tileAreaNames.add(tileAreaName);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                // Handle SQL file
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `tile_area_name`")) {
                        int startIndex = line.indexOf("('") + 2;
                        int endIndex = line.indexOf("')", startIndex);

                        if (startIndex > 1 && endIndex > startIndex) {
                            String areaName = line.substring(startIndex, endIndex).trim();
                            TileAreaName tileAreaName = new TileAreaName();
                            tileAreaName.setAreaName(areaName);
                            tileAreaNames.add(tileAreaName);
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }
            // Save all extracted area names to the database
            tileAreaNameService.saveAll(tileAreaNames);
            return "File uploaded successfully! " + tileAreaNames.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
}
