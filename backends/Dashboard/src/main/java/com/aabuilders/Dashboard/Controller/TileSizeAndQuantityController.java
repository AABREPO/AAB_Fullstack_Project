package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.TileSizeAndQuantity;
import com.aabuilders.Dashboard.Service.TileSizeAndQuantityService;
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
public class TileSizeAndQuantityController {
    @Autowired
    private TileSizeAndQuantityService tileSizeAndQuantityService;
    @PostMapping("/size/quantity")
    public TileSizeAndQuantity saveTileSizeAndQuantity(@RequestBody TileSizeAndQuantity tileSizeAndQuantity){
        return tileSizeAndQuantityService.saveTileSizeAndQuantity(tileSizeAndQuantity);
    }
    @GetMapping("/quantity/size")
    public List<TileSizeAndQuantity> getAllTileSizeAndQuantity(){return  tileSizeAndQuantityService.getAllTileSizeAndQuantity();}
    @PutMapping("/size/quantity/{id}")
    public TileSizeAndQuantity updateTileSizeAndQuantity(@PathVariable Long id, @RequestBody TileSizeAndQuantity tileSizeAndQuantity) {
        return tileSizeAndQuantityService.updateTileSizeAndQuantity(id, tileSizeAndQuantity);
    }
    @DeleteMapping("/size/quantity/{id}")
    public void deleteTileSizeAndQuantity(@PathVariable Long id) {
        tileSizeAndQuantityService.deleteTileSizeAndQuantity(id);
    }
    @DeleteMapping("/size/quantity/all")
    public String deleteAllTileSizeAndQuantity() {
        try {
            tileSizeAndQuantityService.deleteAllTileSizeAndQuantities();
            return "All TileSizeAndQuantity data has been deleted successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to delete all data: " + e.getMessage();
        }
    }

    @PostMapping("/sizeBulkUpload")
    public String uploadTileSizeAndQuantity(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file!";
        }

        List<TileSizeAndQuantity> tileSizeAndQuantities = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();

            if (filename != null && filename.endsWith(".csv")) {
                // Handle CSV file
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 3) {
                        TileSizeAndQuantity tileSizeAndQuantity = new TileSizeAndQuantity();
                        tileSizeAndQuantity.setTileSize(values[0].trim());
                        tileSizeAndQuantity.setQuantityBox(values[1].trim());
                        tileSizeAndQuantity.setAreaTile(values[2].trim());
                        tileSizeAndQuantities.add(tileSizeAndQuantity);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                // Handle SQL file
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `tile_size_and_quantity`")) {
                        int startIndex = line.indexOf("VALUES ('") + 8;
                        int endIndex = line.indexOf("');", startIndex);

                        if (startIndex > 1 && endIndex > startIndex) {
                            String[] values = line.substring(startIndex, endIndex).split("', '");

                            if (values.length == 3) {
                                TileSizeAndQuantity tileSizeAndQuantity = new TileSizeAndQuantity();

                                // Remove leading single quote if it exists in each field
                                tileSizeAndQuantity.setTileSize(removeLeadingSingleQuote(values[0]));
                                tileSizeAndQuantity.setQuantityBox(removeLeadingSingleQuote(values[1]));
                                tileSizeAndQuantity.setAreaTile(removeLeadingSingleQuote(values[2]));

                                tileSizeAndQuantities.add(tileSizeAndQuantity);
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }

            // Save all extracted entries to the database
            tileSizeAndQuantityService.saveAll(tileSizeAndQuantities);
            return "File uploaded successfully! " + tileSizeAndQuantities.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }

    // Helper method to remove the leading single quote if it exists
    private String removeLeadingSingleQuote(String value) {
        if (value.startsWith("'")) {
            return value.substring(1);
        }
        return value;
    }



}
