package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Model.RccConcreteCalculation;
import com.aabuilders.Dashboard.Service.RccConcreteCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rcc_concrete")
public class RccConcreteCalculationController {
    @Autowired
    private RccConcreteCalculationService rccConcreteCalculationService;
    @PostMapping("/save")
    public RccConcreteCalculation saveRccConcreteCalculation(@RequestBody RccConcreteCalculation rccConcreteCalculation){
        return rccConcreteCalculationService.saveRccConcreteCalculation(rccConcreteCalculation);
    }
    @GetMapping("/getAll")
    public List<RccConcreteCalculation> getAllRccConcreteCalculation(){
        return rccConcreteCalculationService.getAllRccConcreteCalculation();
    }
}
