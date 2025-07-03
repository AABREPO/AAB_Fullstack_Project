package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.PaintNameAndImage;
import com.aabuilders.Dashboard.Repository.PaintNameAndImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaintNameAndImageService {
    @Autowired
    private PaintNameAndImageRepository paintNameAndImageRepository;

    public PaintNameAndImage savePaintData(String paintColor, byte[] paintImage){
        PaintNameAndImage paint = new PaintNameAndImage();
        paint.setPaintColor(paintColor);
        paint.setPaintImage(paintImage);
        return paintNameAndImageRepository.save(paint);
    }

    public List<PaintNameAndImage> getAllPaints(){return  paintNameAndImageRepository.findAll();}

    public PaintNameAndImage updatePaintData(Long id, String paintColor, byte[] paintImage){
        PaintNameAndImage paint = paintNameAndImageRepository.findById(id).orElseThrow(() -> new RuntimeException("Paint not found"));
        paint.setPaintColor(paintColor);
        if(paintImage !=null) {
            paint.setPaintImage(paintImage);
        }
        return paintNameAndImageRepository.save(paint);
    }
    public String uploadPaintDetails(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please upload a file!";
        }
        List<PaintNameAndImage> paintDetails = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();
            String line;

            if (filename != null && filename.endsWith(".csv")) {
                // Handle CSV file
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length >= 1) {
                        PaintNameAndImage paintDetail = new PaintNameAndImage();
                        paintDetail.setPaintColor(values[0].trim());  // Only save the paint color name
                        paintDetails.add(paintDetail);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                // Handle SQL file
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `paint_color_and_image`")) {
                        // Parse the SQL 'INSERT' statement to extract only the value for paint_color
                        int valuesStart = line.indexOf("VALUES (") + 8; // Skip 'VALUES ('
                        int valuesEnd = line.indexOf(")", valuesStart); // Find closing parenthesis
                        if (valuesStart > 0 && valuesEnd > valuesStart) {
                            String valuesPart = line.substring(valuesStart, valuesEnd);
                            String[] parts = valuesPart.split(",");
                            if (parts.length >= 1) {
                                // Remove extra spaces and single quotes around the value
                                String paintColor = parts[0].replace("'", "").trim();
                                if (!paintColor.isEmpty()) {
                                    PaintNameAndImage paintDetail = new PaintNameAndImage();
                                    paintDetail.setPaintColor(paintColor); // Save only the paint color value
                                    paintDetails.add(paintDetail);
                                }
                            }
                        }
                    }
                }
            } else {
                return "Unsupported file type. Please upload a CSV or SQL file.";
            }

            // Save to repository if there are valid entries
            if (!paintDetails.isEmpty()) {
                paintNameAndImageRepository.saveAll(paintDetails);
                return "File uploaded successfully! " + paintDetails.size() + " records saved.";
            } else {
                return "No valid data found in the file.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }

    public void deletePaintData(Long id){
        paintNameAndImageRepository.deleteById(id);
    }
    public void deleteAllPaintData(){
        paintNameAndImageRepository.deleteAll();
    }
    public void saveAll(List<PaintNameAndImage> paintDataList){
        paintNameAndImageRepository.saveAll(paintDataList);
    }
}
