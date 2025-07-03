package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.Res;
import com.aabuilders.Dashboard.Service.AgreementPdfGoogleDriveUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
@RestController
@RequestMapping("/agreement/googleUploader")
public class AgreementPdfUploadController {

    @Autowired
    private AgreementPdfGoogleDriveUploadService service;

    @PostMapping("/uploadToGoogleDrive")
    public Object handleFileUpload(
            @RequestParam("pdf") MultipartFile file,
            @RequestParam("filename") String filename
    ) throws IOException, GeneralSecurityException {
        if (file.isEmpty()) {
            return "File is empty";
        }
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile);

        Res res = service.uploadPdfToDrive(tempFile, filename); // pass filename here

        System.out.println(res);
        return res;
    }
}
