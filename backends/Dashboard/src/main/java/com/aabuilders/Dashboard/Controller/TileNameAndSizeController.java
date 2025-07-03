package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.DTO.TileNameAndSizeEdit;  // Import the DTO
import com.aabuilders.Dashboard.Entity.TileNameAndSize;
import com.aabuilders.Dashboard.Service.TileNameAndSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tile-name-size")
public class TileNameAndSizeController {

    @Autowired
    private TileNameAndSizeService tileNameAndSizeService;

    @PostMapping("/add")
    public ResponseEntity<TileNameAndSize> addTile(@RequestBody TileNameAndSize tileNameAndSize) {
        try {
            TileNameAndSize savedTile = tileNameAndSizeService.saveTile(tileNameAndSize);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTile);
        } catch (Exception e) {
            System.err.println("Error saving tile: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/all")
    public List<TileNameAndSize> getAllTiles() {
        return tileNameAndSizeService.getAllTiles();
    }

    @PutMapping("/update/{id}")
    public String updateTile(@PathVariable Long id, @RequestBody TileNameAndSizeEdit tileEdit) {
        boolean updated = tileNameAndSizeService.updateTile(id, tileEdit);
        return updated ? "Tile updated successfully!" : "Tile not found!";
    }

}
