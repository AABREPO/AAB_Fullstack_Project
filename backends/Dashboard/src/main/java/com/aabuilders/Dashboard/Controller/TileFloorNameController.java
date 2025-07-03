package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.TileFloorName;
import com.aabuilders.Dashboard.Service.TileFloorNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/tile")
public class TileFloorNameController {
    @Autowired
    private TileFloorNameService tileFloorNameService;
    @PostMapping("/nameFloor")
    public TileFloorName saveTileFloorName(@RequestBody TileFloorName tileFloorName){
        return tileFloorNameService.saveTileFloorName(tileFloorName);
    }

    @GetMapping("/floorName")
    public List<TileFloorName> getAllTileFloorName(){ return tileFloorNameService.getAllTileFloorName();}
    @PutMapping("/nameFloor/{id}")
    public TileFloorName updateTileFloorName(@PathVariable Long id, @RequestBody TileFloorName tileFloorName) {
        return tileFloorNameService.updateTileFloorName(id, tileFloorName);
    }
    @DeleteMapping("/nameFloor/{id}")
    public void deleteTileFloorName(@PathVariable Long id) {
        tileFloorNameService.deleteTileFloorName(id);
    }
    @PostMapping("/floorNameBulkUpload")
    public String uploadTileFloorNames(@RequestParam("file") MultipartFile file) {
        return tileFloorNameService.uploadTileFloorNames(file);
    }
    @DeleteMapping("/nameFloor/all")
    public String deleteAllTileFloorNames() {
        try {
            tileFloorNameService.deleteAllTileFloorNames();
            return "All tile floor names have been deleted successfully.";
        } catch (Exception e) {
            return "Error occurred while deleting all tile floor names: " + e.getMessage();
        }
    }

}
