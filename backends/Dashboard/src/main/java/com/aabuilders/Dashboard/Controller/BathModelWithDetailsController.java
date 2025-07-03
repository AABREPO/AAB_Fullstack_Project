package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.BathModelWithDetails;
import com.aabuilders.Dashboard.Service.BathModelWithDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/bath_model")
public class BathModelWithDetailsController {

    @Autowired
    private BathModelWithDetailsService bathService;

    // CREATE
    @PostMapping("/save")
    public ResponseEntity<BathModelWithDetails> saveBathModel(
            @RequestParam String brandName,
            @RequestParam String itemName,
            @RequestParam String model,
            @RequestParam String type,
            @RequestParam String price,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(required = false) MultipartFile technicalImage
    ) {
        try {
            byte[] imageBytes = image != null ? image.getBytes() : null;
            byte[] techImageBytes = technicalImage != null ? technicalImage.getBytes() : null;

            BathModelWithDetails saved = bathService.saveBathModelDetails(
                    brandName, itemName, model, type, price, imageBytes, techImageBytes
            );
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    // GET ALL
    @GetMapping("/getAll")
    public ResponseEntity<List<BathModelWithDetails>> getAllBathModels() {
        return ResponseEntity.ok(bathService.getAllBathWithDetails());
    }

    // UPDATE
    @PutMapping("/edit/{id}")
    public ResponseEntity<BathModelWithDetails> updateBathModel(
            @PathVariable Long id,
            @RequestParam String brandName,
            @RequestParam String itemName,
            @RequestParam String model,
            @RequestParam String type,
            @RequestParam String price,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(required = false) MultipartFile technicalImage
    )   {
        try {
            byte[] imageBytes = image != null ? image.getBytes() : null;
            byte[] techImageBytes = technicalImage != null ? technicalImage.getBytes() : null;

            BathModelWithDetails updated = bathService.updateBathModelWithDetails(
                    id, brandName, itemName, model, type, price, imageBytes, techImageBytes
            );
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // DELETE ONE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBathModel(@PathVariable Long id) {
        bathService.deleteBathModelWithDetails(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE ALL
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllBathModels() {
        bathService.deleteAllBathModelWithDetails();
        return ResponseEntity.noContent().build();
    }

    // SAVE MULTIPLE
    @PostMapping("/saveAll")
    public ResponseEntity<Void> saveAll(@RequestBody List<BathModelWithDetails> list) {
        bathService.saveAll(list);
        return ResponseEntity.ok().build();
    }
}
