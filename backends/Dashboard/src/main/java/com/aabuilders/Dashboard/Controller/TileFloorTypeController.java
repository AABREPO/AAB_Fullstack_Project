package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.TileFloorType;
import com.aabuilders.Dashboard.Service.TileFloorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/tile")
public class TileFloorTypeController {
    @Autowired
    private TileFloorTypeService tileFloorTypeService;
    @PostMapping("/typeFloor")
    public TileFloorType saveTileFloorType(@RequestBody TileFloorType tileFloorType){
        return tileFloorTypeService.saveTileFloorType(tileFloorType);
    }
    @GetMapping("/floorType")
    public List<TileFloorType> getAllTileFloorType(){ return tileFloorTypeService.getAllTileFloorType();}
    @PutMapping("/typeFloor/{id}")
    public TileFloorType updateTileFloorType(@PathVariable Long id, @RequestBody TileFloorType tileFloorType) {
        return tileFloorTypeService.updateTileFloorType(id, tileFloorType);
    }
    @DeleteMapping("/typeFloor/{id}")
    public void deleteTileFloorType(@PathVariable Long id) {
        tileFloorTypeService.deleteTileFloorType(id);
    }
    @PostMapping("/bulkUploadType")
    public String uploadTileFloorTypes(@RequestParam("file") MultipartFile file) {
        return tileFloorTypeService.uploadTileFloorTypes(file);
    }
    @DeleteMapping("/nameFloorType/all")
    public String deleteAllTileFloorTypes() {
        try {
            tileFloorTypeService.deleteAllTileFloorTypes();
            return "All tile floor types have been deleted successfully.";
        } catch (Exception e) {
            return "Error occurred while deleting all tile floor types: " + e.getMessage();
        }
    }
}
