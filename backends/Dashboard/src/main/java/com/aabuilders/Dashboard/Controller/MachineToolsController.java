package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.MachineTools;
import com.aabuilders.Dashboard.Service.MachineToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/machine_tools")
public class MachineToolsController {
    @Autowired
    private MachineToolsService machineToolsService;

    @PostMapping("/save")
    public MachineTools saveMachineTools(@RequestBody MachineTools machineTools){
        return machineToolsService.saveMachineTools(machineTools);
    }
    @GetMapping("/getAll")
    public List<MachineTools> getAllMachineTools(){
        return machineToolsService.getAllMachineTools();
    }
    @PutMapping("/edit/{id}")
    public MachineTools updateTools(@PathVariable Long id, @RequestBody MachineTools machineTools){
        return machineToolsService.updateMachineTools(id, machineTools);
    }
    @PostMapping("/bulk_Upload")
    public String updateMachineToolsData(@RequestParam("file")MultipartFile file){
        return machineToolsService.UploadMachineToolsData(file);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllMachineTools(){
        machineToolsService.deleteAllMachineTools();
        return "All Machine Tools deleted Successfully!!";
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMachineTools(@PathVariable Long id){
        machineToolsService.deleteMachineTools(id);
    }
}
