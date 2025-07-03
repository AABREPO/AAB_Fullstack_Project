package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Model.TilePdfLocal;
import com.aabuilders.Dashboard.Repository.TilePdfLocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TilePdfLocalService {

    @Autowired
    private TilePdfLocalRepository tilePdfLocalRepository;

    // Method to get the current increment for a specific file label, type, and clientId
    public int getIncrement(String fileLabel, String fileType, String clientId) {
        return tilePdfLocalRepository.findByFileLabelAndFileTypeAndClientId(fileLabel, fileType, clientId)
                .map(TilePdfLocal::getIncrement).orElse(1); // Default to 1 if not found
    }
    public void updateIncrement(String fileLabel, String fileType, String clientId) {
        TilePdfLocal tilePdfLocal = tilePdfLocalRepository.findByFileLabelAndFileTypeAndClientId(fileLabel, fileType, clientId)
                .orElse(new TilePdfLocal(fileLabel, fileType, 1, clientId)); // Start at 1 if not found

        tilePdfLocal.setIncrement(tilePdfLocal.getIncrement() + 1);
        tilePdfLocalRepository.save(tilePdfLocal);
    }
}
