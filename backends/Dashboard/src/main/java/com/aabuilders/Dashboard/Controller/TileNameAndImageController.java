package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.TileNameAndImage;
import com.aabuilders.Dashboard.Service.TileNameAndImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tiles")
public class TileNameAndImageController {

    @Autowired
    private TileNameAndImageService service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTileData(
            @RequestParam("tileName") String tileName,
            @RequestParam("tileSize") String tileSize,
            @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {

        byte[] imageData = (file != null && !file.isEmpty()) ? file.getBytes() : null;
        service.saveTileData(tileName, tileSize, imageData);

        return ResponseEntity.ok("Tile data uploaded successfully");
    }
    @GetMapping("/all/data")
    public ResponseEntity<List<TileNameAndImage>> getAllTiles() {
        List<TileNameAndImage> tiles = service.getAllTiles();
        return ResponseEntity.ok(tiles);
    }
    @PostMapping("/tileNameBulkUpload")
    public ResponseEntity<String> bulkUploadTileData(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }
        List<TileNameAndImage> tileDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String filename = file.getOriginalFilename();

            if (filename != null && filename.endsWith(".csv")) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 2) {
                        TileNameAndImage tile = new TileNameAndImage();
                        tile.setTileName(removeSingleQuotes(values[0].trim()));
                        tile.setTileSize(removeSingleQuotes(values[1].trim()));
                        tile.setImage(null); // No image, set as null
                        tileDataList.add(tile);
                    }
                }
            } else if (filename != null && filename.endsWith(".sql")) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `tile_name_and_image`")) {
                        int startIndex = line.indexOf("VALUES ('") + 8;
                        int endIndex = line.indexOf("');", startIndex);

                        if (startIndex > 1 && endIndex > startIndex) {
                            String[] values = line.substring(startIndex, endIndex).split("', '");
                            if (values.length >= 2) {
                                TileNameAndImage tile = new TileNameAndImage();
                                tile.setTileName(removeSingleQuotes(values[0].trim()));
                                tile.setTileSize(removeSingleQuotes(values[1].trim()));
                                tile.setImage(null); // No image, set as null
                                tileDataList.add(tile);
                            }
                        }
                    }
                }
            } else {
                return ResponseEntity.badRequest().body("Unsupported file type. Please upload a CSV or SQL file.");
            }

            service.saveAll(tileDataList);
            return ResponseEntity.ok("Bulk upload successful! " + tileDataList.size() + " records saved.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<String> updateTileData(
            @PathVariable Long id,
            @RequestParam("tileName") String tileName,
            @RequestParam("tileSize") String tileSize,
            @RequestParam(value = "image", required = false) MultipartFile file) throws IOException {

        byte[] imageData = (file != null && !file.isEmpty()) ? file.getBytes() : null;
        service.updateTileData(id, tileName, tileSize, imageData);

        return ResponseEntity.ok("Tile data updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTile(@PathVariable Long id) {
        service.deleteTileData(id);
        return ResponseEntity.ok("Tile data deleted successfully");
    }

    @DeleteMapping("/allTile")
    public ResponseEntity<String> deleteAllTiles() {
        service.deleteAllTileData();
        return ResponseEntity.ok("All tile data deleted successfully");
    }

    private String removeSingleQuotes(String value) {
        if (value.startsWith("'")) {
            return value.substring(1);
        }
        return value;
    }
}
