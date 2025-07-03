package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.BathBrandNames;
import com.aabuilders.Dashboard.Service.BathBrandNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/brand_names")
public class BathBrandNameController {

    @Autowired
    private BathBrandNameService bathBrandNameService;

    // Create a new brand name
    @PostMapping("/save")
    public ResponseEntity<BathBrandNames> saveBrandName(@RequestBody BathBrandNames brandName) {
        BathBrandNames saved = bathBrandNameService.saveBathBrandNames(brandName);
        return ResponseEntity.ok(saved);
    }

    // Get all brand names
    @GetMapping("/getAll")
    public ResponseEntity<List<BathBrandNames>> getAllBrandNames() {
        return ResponseEntity.ok(bathBrandNameService.getAllBathBrandNames());
    }

    // Update a brand name
    @PutMapping("/edit/{id}")
    public ResponseEntity<BathBrandNames> updateBrandName(
            @PathVariable Long id,
            @RequestBody BathBrandNames brandName
    ) {
        try {
            BathBrandNames updated = bathBrandNameService.updateBathBrands(id, brandName);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a brand name by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBrandName(@PathVariable Long id) {
        try {
            bathBrandNameService.deleteBathBrandName(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete all brand names
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllBrandNames() {
        bathBrandNameService.deleteAllBrandNames();
        return ResponseEntity.noContent().build();
    }

    // Upload brand names via file (.csv or .sql)
    @PostMapping("/bulkUpload")
    public ResponseEntity<String> uploadBrandNames(@RequestParam("file") MultipartFile file) {
        String result = bathBrandNameService.uploadBrandNames(file);
        return ResponseEntity.ok(result);
    }
}
