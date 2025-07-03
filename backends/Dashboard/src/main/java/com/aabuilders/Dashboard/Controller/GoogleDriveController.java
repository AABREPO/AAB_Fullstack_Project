package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Service.BathFixturesPdfUploaderService;
import com.aabuilders.Dashboard.Service.GoogleDriveService;
import com.aabuilders.Dashboard.Service.PaintCalculatorPdfUploaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/googleUploader")
public class GoogleDriveController {
    @Autowired
    private GoogleDriveService googleDriveService;

    @Autowired
    private PaintCalculatorPdfUploaderService paintCalculatorPdfUploaderService;

    @Autowired
    private BathFixturesPdfUploaderService bathFixturesPdfUploaderService;

    @PostMapping("/pdfs")
    public ResponseEntity<String> uploadFiles(@RequestParam("files") MultipartFile file) {
        try {
            String fileIds = googleDriveService.uploadFiles(file);
            return ResponseEntity.ok(fileIds);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    @PostMapping("/paintPdfs")
    public ResponseEntity<String> uploadToPaintCalculatorFolder(@RequestParam("files") MultipartFile file) {
        try {
            String fileId = paintCalculatorPdfUploaderService.uploadPdf(file);
            return ResponseEntity.ok(fileId);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file to Paint Calculator folder");
        }
    }
    @PostMapping("/bathPdfs")
    public ResponseEntity<String> uploadToBathFixtureFolder(@RequestParam("files") MultipartFile file){
        try {
            String fileId = bathFixturesPdfUploaderService.uploadPdf(file);
            return ResponseEntity.ok(fileId);
        } catch (IOException e){
            return ResponseEntity.status(500).body("Failed to upload file to Bath Fixture Folder");
        }
    }
}
