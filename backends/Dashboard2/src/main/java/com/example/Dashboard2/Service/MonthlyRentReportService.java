package com.example.Dashboard2.Service;

import com.example.Dashboard2.Entity.Res;
import com.example.Dashboard2.Repository.MonthlyRentReportRepo;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.AbstractInputStreamContent;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class MonthlyRentReportService {

    @Autowired
    private MonthlyRentReportRepo monthlyRentReportRepo;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_PATH = getPathToGoogleCredentials();

    private static String getPathToGoogleCredentials() {
        String currentDirectory = System.getProperty("user.dir");
        Path filePath = Paths.get(currentDirectory, "cred.json");
        return filePath.toString();
    }

    public int getNextMonthlyReportNumber(){
        Integer max = monthlyRentReportRepo.findMaxReportNumber();
        return (max !=null ? max : 0) + 1;
    }

    public Res uploadPdfToDrive(InputStream inputStream, String filename) throws GeneralSecurityException {
        Res res = new Res();
        try {
            String folderId = "1Mfnvn3kMKz-ePkzh1U-X6JMTZq67LuPA";
            Drive drive = createDriveService();

            com.google.api.services.drive.model.File fileMetaData = new com.google.api.services.drive.model.File();
            fileMetaData.setName(filename);
            fileMetaData.setParents(Collections.singletonList(folderId));

            AbstractInputStreamContent mediaContent = new InputStreamContent("application/pdf", inputStream);

            com.google.api.services.drive.model.File uploadedFile = drive.files()
                    .create(fileMetaData, mediaContent)
                    .setFields("id")
                    .execute();
            String pdfUrl = "https://drive.google.com/file/d/" + uploadedFile.getId() + "/view?usp=drive_web";
            res.setStatus(200);
            res.setMessage("PDF Successfully Uploaded To Drive");
            res.setUrl(pdfUrl);
        } catch (Exception e){
            res.setStatus(500);
            res.setMessage(e.getMessage());
        }
        return res;
    }
    private Drive createDriveService() throws GeneralSecurityException {
        try (FileInputStream serviceAccountStream = new FileInputStream(SERVICE_ACCOUNT_KEY_PATH)) {
            GoogleCredential credential = GoogleCredential.fromStream(serviceAccountStream)
                    .createScoped(Collections.singleton(DriveScopes.DRIVE));
            return new Drive.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, credential).build();
        } catch (Exception e) {
            throw new GeneralSecurityException("Failed to create Drive service", e);
        }
    }

}
