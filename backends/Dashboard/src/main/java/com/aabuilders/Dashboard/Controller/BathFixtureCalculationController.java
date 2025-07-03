package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Model.BathFixtureCalculation;
import com.aabuilders.Dashboard.Service.BathFixtureCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bath_fixture_calculation")
public class BathFixtureCalculationController {
    @Autowired
    private BathFixtureCalculationService bathFixtureCalculationService;

    @PostMapping("/save")
    public BathFixtureCalculation saveBathFixtureCalculation(@RequestBody BathFixtureCalculation bathFixtureCalculation){
        return bathFixtureCalculationService.saveBathFixtureCalculation(bathFixtureCalculation);
    }

    @GetMapping("/all")
    public List<BathFixtureCalculation> getAllBathFixtureCalculations(){
        return bathFixtureCalculationService.getAllBathFixtureCalculation();
    }
    @PutMapping("/edit/{id}")
    public BathFixtureCalculation editBathFixtures(@PathVariable Long id, @RequestBody BathFixtureCalculation updatedBathFixtureCalculation){
        return bathFixtureCalculationService.editBathFixtureCalculation(id, updatedBathFixtureCalculation);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteBathFixtures(@PathVariable Long id){
        return bathFixtureCalculationService.deleteBathFixture(id);
    }
    @DeleteMapping("/delete/all")
    public String deleteAllBathFixtures(){
        return bathFixtureCalculationService.deleteAllBathFixture();
    }
}
