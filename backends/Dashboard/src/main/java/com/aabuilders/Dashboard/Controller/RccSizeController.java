package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.RccSize;
import com.aabuilders.Dashboard.Entity.TileSizeAndQuantity;
import com.aabuilders.Dashboard.Service.RccSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/rcc/size")
public class RccSizeController {

    @Autowired
    private RccSizeService rccSizeService;

    @PostMapping("/save")
    public RccSize saveRccSize(@RequestBody RccSize rccSize){
        return rccSizeService.saveRccSize(rccSize);
    }
    @GetMapping("/get")
    public List<RccSize> getAllRccSizes(){
        return rccSizeService.getAllRccSize();
    }
    @PutMapping("/edit/{id}")
    public RccSize updateRccSize(@PathVariable Long id, @RequestBody RccSize rccSize){
        return rccSizeService.updateRccSize(id, rccSize);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteRccSize(@PathVariable Long id){
        rccSizeService.deleteRccSize(id);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllRccSize(){
        try {
            rccSizeService.deleteAllRccSize();
            return "All Rcc Size has been deleted successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed to delete all data: " + e.getMessage();
        }
    }
    @PostMapping("/bulkUpload")
    public String uploadRccSize(@RequestParam("file")MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file!";
        }

        List<RccSize> rccSizes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();

            if (filename != null && filename.endsWith(".csv")) {
                // Handle CSV file
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 1) {
                        RccSize rccSize = new RccSize();
                        rccSize.setSize(values[0].trim());
                        rccSizes.add(rccSize);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                // Handle SQL file
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `rcc_size`")) {
                        int startIndex = line.indexOf("VALUES ('") + 8;
                        int endIndex = line.indexOf("');", startIndex);

                        if (startIndex > 1 && endIndex > startIndex) {
                            String[] values = line.substring(startIndex, endIndex).split("', '");

                            if (values.length == 1) {
                                RccSize rccSize = new RccSize();
                                rccSize.setSize(removeLeadingSingleQuote(values[0]));
                                rccSizes.add(rccSize);
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }

            // Save all extracted entries to the database
            rccSizeService.saveAll(rccSizes);
            return "File uploaded successfully! " + rccSizes.size() + " records saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }
        private String removeLeadingSingleQuote(String value) {
            if (value.startsWith("'")) {
                return value.substring(1);
            }
            return value;
        }
}

