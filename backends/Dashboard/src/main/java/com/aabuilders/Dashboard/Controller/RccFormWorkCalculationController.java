package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Model.RccFormWorkCalculation;
import com.aabuilders.Dashboard.Service.RccFormWorkCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rcc_formWork")
public class RccFormWorkCalculationController {
    @Autowired
    private RccFormWorkCalculationService rccFormWorkCalculationService;
    @PostMapping("/save/form_work")
    public RccFormWorkCalculation saveRccFormWorkCalculation(@RequestBody RccFormWorkCalculation rccFormWorkCalculation){
        return rccFormWorkCalculationService.saveRccFormWorkCalculation(rccFormWorkCalculation);
    }
    @GetMapping("/getAll")
    public List<RccFormWorkCalculation> getAllRccFormWorkCalculation(){
        return rccFormWorkCalculationService.getAllRccFormWorkCalculation();
    }
}
