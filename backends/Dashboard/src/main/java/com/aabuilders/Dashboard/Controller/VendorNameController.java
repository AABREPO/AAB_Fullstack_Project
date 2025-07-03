package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.VendorNames;
import com.aabuilders.Dashboard.Service.VendorNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/vendor_Names")
public class VendorNameController {
    @Autowired
    private VendorNameService vendorNameService;

    @PostMapping("/save")
    public VendorNames saveVendorNames(@RequestBody VendorNames vendorNames){
        return vendorNameService.saveVendorName(vendorNames);
    }
    @GetMapping("/getAll")
    public List<VendorNames> getAllVendorName(){
        return vendorNameService.getAllVendorNames();
    }
    @PutMapping("/edit/{id}")
    public VendorNames updateVendor(@PathVariable Long id, @RequestBody VendorNames vendorNames){
        return vendorNameService.updateVendorNames(id, vendorNames);
    }
    @PostMapping("/bulk_upload")
    public String uploadVendorNameData(@RequestParam("file")MultipartFile file){
        return vendorNameService.uploadVendorNameData(file);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteVendor(@PathVariable Long id){
        vendorNameService.deleteVendorName(id);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllVendorName(){
        vendorNameService.deleteAllVendorNames();
        return "All Vendor Name deleted";
    }
}
