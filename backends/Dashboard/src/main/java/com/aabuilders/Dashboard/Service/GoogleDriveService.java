package com.aabuilders.Dashboard.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleDriveService {
    private static final String APPLICATION_NAME = "Tile PDF Uploader";
    private static final String CREDENTIALS_FILE_PATH = "cred.json";
    private static final String FOLDER_ID = "1j8zZ8bYiLj5iXDyJ3SXcE_l5EbiA0fLH";
    private Drive driveService;

    public GoogleDriveService() throws GeneralSecurityException, IOException {
        this.driveService = getDriveService();
    }

    private Drive getDriveService() throws GeneralSecurityException, IOException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH))
                .createScoped(Collections.singleton(DriveScopes.DRIVE_FILE));

        return new Drive.Builder(credential.getTransport(), credential.getJsonFactory(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public String uploadFiles(MultipartFile file) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName(file.getOriginalFilename());
        fileMetadata.setParents(Collections.singletonList(FOLDER_ID));
        File uploadedFile = driveService.files().create(fileMetadata,
                        new InputStreamContent("application/pdf", file.getInputStream()))
                .setFields("id")
                .execute();
        return uploadedFile.getId();
    }
}
