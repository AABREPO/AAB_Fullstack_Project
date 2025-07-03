package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.PaintVariant;
import com.aabuilders.Dashboard.Service.PaintVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/paint")
public class PaintVariantController {
    @Autowired
    private PaintVariantService paintVariantService;
    @PostMapping("/variant/save")
    public PaintVariant savePaintVariant(@RequestBody PaintVariant paintVariant) {
        return paintVariantService.savePaintVariant(paintVariant);
    }
    @GetMapping("/variant/get/all")
    public List<PaintVariant> getAllPaintVariants() {
        return paintVariantService.getAllPaintVariants();
    }
    @PutMapping("/variant/update/{id}")
    public PaintVariant updatePaintVariant(@PathVariable Long id, @RequestBody PaintVariant paintVariant) {
        return paintVariantService.updatePaintVariant(id, paintVariant);
    }
    @DeleteMapping("/variant/delete/{id}")
    public void deletePaintVariant(@PathVariable Long id) {
        paintVariantService.deletePaintVariant(id);
    }
    @PostMapping("/bulkUploadPaintVariants")
    public String uploadPaintVariants(@RequestParam("file") MultipartFile file) {
        return paintVariantService.uploadPaintVariants(file);
    }
    @DeleteMapping("/variant/delete/all")
    public String deleteAllPaintVariants() {
        try {
            paintVariantService.deleteAllPaintVariants();
            return "All paint variants have been deleted successfully.";
        } catch (Exception e) {
            return "Error occurred while deleting all paint variants: " + e.getMessage();
        }
    }
}
