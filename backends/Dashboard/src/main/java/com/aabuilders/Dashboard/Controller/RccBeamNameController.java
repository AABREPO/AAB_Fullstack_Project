package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.RccBeamName;
import com.aabuilders.Dashboard.Service.RccBeamNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rcc")
public class RccBeamNameController {
    @Autowired
    private RccBeamNameService rccBeamNameService;
    @PostMapping("/upload/beamName")
    public ResponseEntity<String> uploadRccBeamNames(
            @RequestParam("beamName") String beamName,
            @RequestParam("formula") String formula,
            @RequestParam("rate") String rate,
            @RequestParam(value = "measurementImage", required = false)MultipartFile file) throws IOException{
        byte[] imageData = (file != null && !file.isEmpty())? file.getBytes() : null;
        rccBeamNameService.saveRccBeamName(beamName, formula, rate, imageData);
        return ResponseEntity.ok("Beam Name And Formula Upload Successfully");
    }
    @GetMapping("/all/beamNameData")
    public ResponseEntity<List<RccBeamName>> getAllBeamNames(){
        List<RccBeamName> beamNames = rccBeamNameService.getAllBeamNames();
        return ResponseEntity.ok(beamNames);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateRccBeamNames(
            @PathVariable Long id,
            @RequestParam("beamName") String beamName,
            @RequestParam("formula") String formula,
            @RequestParam("rate") String rate,
            @RequestParam(value = "measurementImage", required = false)MultipartFile file) throws IOException {
        byte[] imageData = (file != null && !file.isEmpty()) ? file.getBytes() : null;
        rccBeamNameService.updatedBeamName(id, beamName, formula, rate, imageData);
        return ResponseEntity.ok("Rcc Beam Name And Formula Updated Successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRccBeamNames(@PathVariable Long id){
        rccBeamNameService.deleteRccBeamName(id);
        return ResponseEntity.ok("Rcc Beam Name And Formulas Deleted Successfully");
    }
    @DeleteMapping("/delete/allRccBeamNames")
    public ResponseEntity<String> deleteAllRccBeamNames(){
        rccBeamNameService.deleteAllRccBeamNames();
        return ResponseEntity.ok("All Rcc Beam Name Data Deleted Successfully");
    }
}
