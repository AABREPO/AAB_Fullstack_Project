package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.PaintType;
import com.aabuilders.Dashboard.Service.PaintTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/paint_type")
public class PaintTypeController {

    @Autowired
    private PaintTypeService paintTypeService;

    // Save a single PaintType
    @PostMapping("/save")
    public PaintType savePaintType(@RequestBody PaintType paintType) {
        return paintTypeService.savePaintType(paintType);
    }

    // Get all PaintTypes
    @GetMapping("/getAll")
    public List<PaintType> getAllPaintType() {
        return paintTypeService.getAllPaintType();
    }

    // Update a PaintType by ID
    @PutMapping("/edit/{id}")
    public PaintType updatePaintType(@PathVariable Long id, @RequestBody PaintType paintType) {
        return paintTypeService.updatePaintType(id, paintType);
    }

    @PostMapping("/bulk_upload")
    public String uploadPaintTypeData(@RequestParam("file") MultipartFile file) {
        return paintTypeService.uploadPaintTypeData(file);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePaintType(@PathVariable Long id) {
        paintTypeService.deletePaintType(id);
        return "PaintType with id " + id + " has been deleted successfully!";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllPaintType() {
        paintTypeService.deleteAllPaintType();
        return "All PaintType records have been deleted successfully!";
    }

    @PostMapping("/saveAll")
    public List<PaintType> saveAllPaintTypes(@RequestBody List<PaintType> paintTypes) {
        return paintTypeService.saveAll(paintTypes);
    }
}