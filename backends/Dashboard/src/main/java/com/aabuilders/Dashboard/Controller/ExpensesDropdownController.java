package com.aabuilders.Dashboard.Controller;
import com.aabuilders.Dashboard.DTO.SiteDTO;
import com.aabuilders.Dashboard.Service.ExpensesDropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/expenses")
public class ExpensesDropdownController {
    @Autowired
    private ExpensesDropdownService service;
    @GetMapping("/sites")
    public ResponseEntity<List<SiteDTO>> getAllSiteNames() {
        List<SiteDTO> sites = service.getAllSiteNames();
        return ResponseEntity.ok(sites);
    }
    @GetMapping("/vendors")
    public ResponseEntity<List<String>> getAllVendors() {
        List<String> vendors = service.getAllVendors();
        return ResponseEntity.ok(vendors);
    }
    @GetMapping("/contractors")
    public ResponseEntity<List<String>> getAllContractors() {
        List<String> contractors = service.getAllContractors();
        return ResponseEntity.ok(contractors);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = service.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/machine-tools")
    public ResponseEntity<List<String>> getAllMachineTools() {
        List<String> machineTools = service.getAllMachineTools();
        return ResponseEntity.ok(machineTools);
    }
    @PostMapping("/sites")
    public ResponseEntity<String> addSiteName(@RequestBody Map<String, String> requestBody) {
        String siteName = requestBody.get("siteName");
        String sNoString = requestBody.get("sNo");  // Retrieve the sNo as a string

        // Ensure that both siteName and sNo are provided
        if (siteName != null && !siteName.isEmpty() && sNoString != null && !sNoString.isEmpty()) {
            try {
                Long sNo = Long.parseLong(sNoString); // Convert the sNo from String to Long
                service.saveSiteName(sNo, siteName);  // Call the service method with both parameters
                return ResponseEntity.ok("Site name and S.No added successfully");
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid format for S.No");
            }
        } else {
            return ResponseEntity.badRequest().body("Both S.No and site name are required");
        }
    }
    @PostMapping("/vendors")
    public ResponseEntity<String> addVendor(@RequestBody Map<String, String> requestBody) {
        String vendor = requestBody.get("vendor");
        if (vendor != null && !vendor.isEmpty()) {
            service.saveVendor(vendor);
            return ResponseEntity.ok("Vendor added successfully");
        } else {
            return ResponseEntity.badRequest().body("Vendor is required");
        }
    }
    @PostMapping("/contractors")
    public ResponseEntity<String> addContractor(@RequestBody Map<String, String> requestBody) {
        String contractor = requestBody.get("contractor");
        if (contractor != null && !contractor.isEmpty()) {
            service.saveContractor(contractor);
            return ResponseEntity.ok("Contractor added successfully");
        } else {
            return ResponseEntity.badRequest().body("Contractor is required");
        }
    }
    @PostMapping("/categories")
    public ResponseEntity<String> addCategory(@RequestBody Map<String, String> requestBody) {
        String category = requestBody.get("category");
        if (category != null && !category.isEmpty()) {
            service.saveCategory(category);
            return ResponseEntity.ok("Category added successfully");
        } else {
            return ResponseEntity.badRequest().body("Category is required");
        }
    }
    @PostMapping("/machine-tools")
    public ResponseEntity<String> addMachineTools(@RequestBody Map<String, String> requestBody) {
        String machineTools = requestBody.get("machineTools");
        if (machineTools != null && !machineTools.isEmpty()) {
            service.saveMachineTools(machineTools);
            return ResponseEntity.ok("Machine tools added successfully");
        } else {
            return ResponseEntity.badRequest().body("Machine tools are required");
        }
    }
}

