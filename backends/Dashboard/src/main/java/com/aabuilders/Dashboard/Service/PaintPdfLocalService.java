package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Model.PaintPdfLocal;
import com.aabuilders.Dashboard.Repository.PaintPdfLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaintPdfLocalService {

    @Autowired
    private PaintPdfLocalRepository paintPdfLocalRepository;

    // Method to get the current increment for a specific file label, type, and clientId
    public int getIncrement(String fileLabel, String fileType, String clientId) {
        return paintPdfLocalRepository.findByFileLabelAndFileTypeAndClientId(fileLabel, fileType, clientId)
                .map(PaintPdfLocal::getIncrement).orElse(1); // Default to 1 if not found
    }

    // Method to update the increment for a specific file label, type, and clientId
    public void updateIncrement(String fileLabel, String fileType, String clientId) {
        PaintPdfLocal paintPdfLocal = paintPdfLocalRepository.findByFileLabelAndFileTypeAndClientId(fileLabel, fileType, clientId)
                .orElse(new PaintPdfLocal(fileLabel, fileType, 1, clientId)); // Start at 1 if not found

        paintPdfLocal.setIncrement(paintPdfLocal.getIncrement() + 1);
        paintPdfLocalRepository.save(paintPdfLocal); // Save the updated entry
    }
}
