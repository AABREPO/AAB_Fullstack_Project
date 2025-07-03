package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.CeilingCoat;
import com.aabuilders.Dashboard.Service.CeilingCoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ceiling_coat")
public class CeilingCoatController {

    private final CeilingCoatService ceilingCoatService;

    @Autowired
    public CeilingCoatController(CeilingCoatService ceilingCoatService) {
        this.ceilingCoatService = ceilingCoatService;
    }

    @PostMapping("/save")
    public ResponseEntity<List<CeilingCoat>> saveCeilingCoats(@RequestBody List<CeilingCoat> ceilingCoats) {
        System.out.println("Received Data: " + ceilingCoats);
        List<CeilingCoat> savedCoats = ceilingCoatService.saveCeilingCoats(ceilingCoats);
        return ResponseEntity.ok(savedCoats);
    }

    // Add this new method to fetch data
    @GetMapping("/getAll")
    public ResponseEntity<List<CeilingCoat>> getAllCeilingCoats() {
        List<CeilingCoat> ceilingCoats = ceilingCoatService.getAllCeilingCoats();
        return ResponseEntity.ok(ceilingCoats);
    }
}
