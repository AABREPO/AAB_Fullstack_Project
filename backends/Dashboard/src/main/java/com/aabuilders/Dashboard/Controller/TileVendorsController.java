package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.TileAreaName;
import com.aabuilders.Dashboard.Entity.TileVendors;
import com.aabuilders.Dashboard.Service.TileVendorService;
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
public class TileVendorsController {
    @Autowired
    private TileVendorService tileVendorService;
    @PostMapping("/vendor/save")
    public TileVendors saveTileVendors(@RequestBody TileVendors tileVendors){
        return tileVendorService.saveTileVendors(tileVendors);
    }
    @GetMapping("/vendor/getAll")
    public List<TileVendors> getAllTileVendors(){
        return tileVendorService.getAllTileVendors();
    }
    @PutMapping("/vendor/{id}")
    public TileVendors updateTileVendors(@PathVariable Long id, @RequestBody TileVendors tileVendors){
        return tileVendorService.updateTileVendors(id, tileVendors);
    }
    @DeleteMapping("/vendor/delete/{id}")
    public void deleteTileVendors(@PathVariable Long id){
        tileVendorService.deleteTileVendors(id);
    }
    @DeleteMapping("/vendor/delete/all")
    public String deleteAllTileVendors(){
        tileVendorService.deleteAllTileVendors();
        return "All TileVendors records have been delected successfully!";
    }
    @PostMapping("/vendor/bulkUpload")
    public String uploadTileVendors(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file!";
        }
        List<TileVendors> tileVendors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            if (filename != null && filename.endsWith(".csv")) {
                // Handle CSV file
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length > 0) {
                        TileVendors tileVendors1 = new TileVendors();
                        tileVendors1.setTileVendor(values[0].trim());
                        tileVendors.add(tileVendors1);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                // Handle SQL file
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `tile_vendors`")) {
                        int startIndex = line.indexOf("('") + 2;
                        int endIndex = line.indexOf("')", startIndex);
                        if (startIndex > 1 && endIndex > startIndex) {
                            String vendors = line.substring(startIndex, endIndex).trim();
                            TileVendors tileVendors1 = new TileVendors();
                            tileVendors1.setTileVendor(vendors);
                            tileVendors.add(tileVendors1);
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }
            // Save all extracted area names to the database
            tileVendorService.saveAll(tileVendors);
            return "File uploaded successfully! " + tileVendors.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
}
