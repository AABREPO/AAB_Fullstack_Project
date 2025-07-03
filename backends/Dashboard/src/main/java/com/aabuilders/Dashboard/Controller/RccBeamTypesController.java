package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.RccBeamTypes;
import com.aabuilders.Dashboard.Entity.TileFloorName;
import com.aabuilders.Dashboard.Service.RccBeamTypesService;
import com.aabuilders.Dashboard.Service.TileFloorNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/beam_types")
public class RccBeamTypesController {
    @Autowired
    private RccBeamTypesService rccBeamTypesService;
    @PostMapping("/save")
    public RccBeamTypes saveRccBeamTypes(@RequestBody RccBeamTypes rccBeamTypes){
        return rccBeamTypesService.saveRccBeamTypes(rccBeamTypes);
    }

    @GetMapping("/getAll")
    public List<RccBeamTypes> getAllRccBeamTypes(){ return rccBeamTypesService.getAllRccBeamTypes();}
    @PutMapping("/edit/{id}")
    public RccBeamTypes updateRccBeamTypes(@PathVariable Long id, @RequestBody RccBeamTypes rccBeamTypes) {
        return rccBeamTypesService.updateRccBeamTypes(id, rccBeamTypes);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteRccBeamTypes(@PathVariable Long id) {
        rccBeamTypesService.deleteRccBeamTypes(id);
    }
    @DeleteMapping("/delete/all")
    public String deleteAllRccBeamTypes() {
        try {
            rccBeamTypesService.deleteAllRccBeamTypes();
            return "All tile floor names have been deleted successfully.";
        } catch (Exception e) {
            return "Error occurred while deleting all tile floor names: " + e.getMessage();
        }
    }

}
