package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.DTO.PaintDataDTO;
import com.aabuilders.Dashboard.Service.PaintDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paintData")
public class PaintDataController {

    @Autowired
    private PaintDataService paintDataService;

    @PostMapping("/extra")
    public void savePaintData(@RequestBody List<PaintDataDTO> paintData) {
        paintDataService.savePaintData(paintData);
    }
    @GetMapping("/allExtra")
    public List<PaintDataDTO> getAllPaintData() {
        return paintDataService.getAllPaintData(); // Fetch and return the list of DTOs
    }
}