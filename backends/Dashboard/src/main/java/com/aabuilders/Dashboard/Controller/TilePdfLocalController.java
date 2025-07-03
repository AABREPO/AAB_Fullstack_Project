package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Model.FileLabelRequest;
import com.aabuilders.Dashboard.Service.TilePdfLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tile/tile")
public class TilePdfLocalController {

    @Autowired
    private TilePdfLocalService tilePdfLocalService;

    // Get the current increment for a specific file label, type, and clientId
    @GetMapping("/increment")
    public ResponseEntity<Integer> getIncrement(@RequestParam String fileLabel,
                                                @RequestParam String fileType,
                                                @RequestParam String clientId) {
        int increment = tilePdfLocalService.getIncrement(fileLabel, fileType, clientId);
        return ResponseEntity.ok(increment);
    }

    // Update increment for a specific file type and clientId
    @PostMapping("/updateIncrement")
    public ResponseEntity<Void> updateIncrement(@RequestBody FileLabelRequest request) {
        tilePdfLocalService.updateIncrement(request.getFileLabel(), request.getFileType(), request.getClientId());
        return ResponseEntity.ok().build();
    }
}
