package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Model.RccSteelCalculation;
import com.aabuilders.Dashboard.Service.RccFormWorkCalculationService;
import com.aabuilders.Dashboard.Service.RccSteelCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rcc_steel")
public class RccSteelCalculationController {
    @Autowired
    private RccSteelCalculationService rccSteelCalculationService;

    @PostMapping("/save/steel")
    public RccSteelCalculation saveRccSteelCalculation(@RequestBody RccSteelCalculation rccSteelCalculation){
        return rccSteelCalculationService.saveRccSteelCalculation(rccSteelCalculation);
    }

}
