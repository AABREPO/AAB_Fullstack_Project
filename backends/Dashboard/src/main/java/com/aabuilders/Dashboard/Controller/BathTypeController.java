package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.BathType;
import com.aabuilders.Dashboard.Service.BathTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/bath_types")
public class BathTypeController {

    @Autowired
    private BathTypeService bathTypeService;

    // Create a new bath type
    @PostMapping("/save")
    public ResponseEntity<BathType> createBathType(@RequestBody BathType bathType) {
        return ResponseEntity.ok(bathTypeService.saveBathType(bathType));
    }

    // Get all bath types
    @GetMapping("/getAll")
    public ResponseEntity<List<BathType>> getAllBathTypes() {
        return ResponseEntity.ok(bathTypeService.getAllBathType());
    }

    // Update a bath type by ID
    @PutMapping("/edit/{id}")
    public ResponseEntity<BathType> updateBathType(@PathVariable Long id, @RequestBody BathType bathType) {
        return ResponseEntity.ok(bathTypeService.updateBathType(id, bathType));
    }

    // Delete a specific bath type by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBathType(@PathVariable Long id) {
        bathTypeService.deleteBathType(id);
        return ResponseEntity.ok("Bath Type with id " + id + " deleted successfully.");
    }

    // Delete all bath types
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllBathTypes() {
        bathTypeService.deleteAllBathType();
        return ResponseEntity.ok("All bath types deleted successfully.");
    }

    // Upload bath types via CSV or SQL
    @PostMapping("/bulkUpload")
    public ResponseEntity<String> uploadBathTypes(@RequestParam("file") MultipartFile file) {
        String response = bathTypeService.uploadBathTypes(file);
        return ResponseEntity.ok(response);
    }
}
