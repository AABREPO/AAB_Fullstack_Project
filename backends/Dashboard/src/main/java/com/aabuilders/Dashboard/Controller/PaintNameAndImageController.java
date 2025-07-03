package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.PaintNameAndImage;
import com.aabuilders.Dashboard.Service.PaintNameAndImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/paints")
public class PaintNameAndImageController {
    @Autowired
    private PaintNameAndImageService paintNameAndImageService;
    @PostMapping("/upload")
    public ResponseEntity<String> uploadPaintData(
            @RequestParam("paintColor") String paintColor,
            @RequestParam(value = "paintImage", required = false)MultipartFile file) throws IOException {
        byte[] imageData = (file != null && !file.isEmpty()) ? file.getBytes() : null;
        paintNameAndImageService.savePaintData(paintColor, imageData);
        return ResponseEntity.ok("Paint Data Upload Successfully");
    }
    @PostMapping("/bulk-upload")
    public ResponseEntity<String> uploadPaintDetails(@RequestParam("file") MultipartFile file) {
        String result = paintNameAndImageService.uploadPaintDetails(file);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/all/data")
    public ResponseEntity<List<PaintNameAndImage>> getAllPaints(){
        List<PaintNameAndImage> paints = paintNameAndImageService.getAllPaints();
        return ResponseEntity.ok(paints);
    }
    @PutMapping("/change/{id}")
    public ResponseEntity<String> updatePaintData(
            @PathVariable Long id,
            @RequestParam("paintColor") String paintColor,
            @RequestParam(value = "paintImage", required = false) MultipartFile file) throws  IOException {
        byte[] imageData = (file != null && !file.isEmpty()) ? file.getBytes() : null;
        paintNameAndImageService.updatePaintData(id, paintColor, imageData);

        return ResponseEntity.ok("Paint data updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTile(@PathVariable Long id){
        paintNameAndImageService.deletePaintData(id);
        return ResponseEntity.ok("Paint data deleted successfully");
    }
    @DeleteMapping("/delete/allTile")
    public ResponseEntity<String> deleteAllPaints(){
        paintNameAndImageService.deleteAllPaintData();
        return ResponseEntity.ok("All paint data deleted successfully");
    }
}
