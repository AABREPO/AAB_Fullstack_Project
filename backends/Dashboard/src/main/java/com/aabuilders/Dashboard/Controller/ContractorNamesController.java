package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.ContractorNames;
import com.aabuilders.Dashboard.Entity.VendorNames;
import com.aabuilders.Dashboard.Service.ContractorNameService;
import com.aabuilders.Dashboard.Service.VendorNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/contractor_Names")
public class ContractorNamesController {
    @Autowired
    private ContractorNameService contractorNameService;

    @PostMapping("/save")
    public ContractorNames saveContractorNames(@RequestBody ContractorNames contractorNames){
        return contractorNameService.saveContractorName(contractorNames);
    }

    @GetMapping("/getAll")
    public List<ContractorNames> getAllContractorName(){
        return contractorNameService.getAllContractorNames();
    }

    @PutMapping("/edit/{id}")
    public ContractorNames updateContractor(@PathVariable Long id, @RequestBody ContractorNames contractorNames){
        return contractorNameService.updateContractorNames(id, contractorNames);
    }

    @PostMapping("/bulk_upload")
    public String uploadContractorNameData(@RequestParam("file") MultipartFile file){
        return contractorNameService.uploadContractorNameData(file);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteContractor(@PathVariable Long id){
        contractorNameService.deleteContractorName(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllContractorName(){
        contractorNameService.deleteAllContractorNames();
        return "All Contractor Name deleted";
    }
}
