package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Model.FileLabelRequest;
import com.aabuilders.Dashboard.Service.PaintPdfLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paint/pdf")
public class PaintPdfLocalController {

    @Autowired
    private PaintPdfLocalService paintPdfLocalService;
    @GetMapping("/increment")
    public ResponseEntity<Integer> getIncrement(@RequestParam String fileLabel,
                                                @RequestParam String fileType,
                                                @RequestParam String clientId) {
        int increment = paintPdfLocalService.getIncrement(fileLabel, fileType, clientId);
        return ResponseEntity.ok(increment);
    }
    @PostMapping("/updateIncrement")
    public ResponseEntity<Void> updateIncrement(@RequestBody FileLabelRequest request) {
        paintPdfLocalService.updateIncrement(request.getFileLabel(), request.getFileType(), request.getClientId());
        return ResponseEntity.ok().build();
    }
}
