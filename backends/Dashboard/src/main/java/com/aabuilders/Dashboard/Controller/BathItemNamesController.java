package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.BathItemNames;
import com.aabuilders.Dashboard.Service.BathItemNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/bath")
public class BathItemNamesController {
    @Autowired
    private BathItemNameService bathItemNameService;
    @PostMapping("/item/save")
    public BathItemNames saveBathItemNames(@RequestBody BathItemNames bathItemNames){
        return bathItemNameService.saveBathItemNames(bathItemNames);
    }

    @GetMapping("/getAll/item")
    public List<BathItemNames> getAllBathItemName(){
        return bathItemNameService.getAllBathItemNames();
    }
    @PutMapping("/edit/{id}")
    public BathItemNames updateBathItemName(@PathVariable Long id, @RequestBody BathItemNames bathItemNames){
        return bathItemNameService.updateBathItemNames(id, bathItemNames);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteBathItemName(@PathVariable Long id){
        bathItemNameService.deleteBathItemName(id);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllBathItemName(){
        try {
            bathItemNameService.deleteAllBathItemNames();
            return "All Bath item name have been deleted successfully.";
        } catch (Exception e){
            return "Error occurred while deleting all bath item names: "+ e.getMessage();
        }
    }
    @PostMapping("/item_name_bulkUpload")
    public String uploadBathItemNames(@RequestParam("file")MultipartFile file){
        return bathItemNameService.uploadBathItemNames(file);
    }
}
