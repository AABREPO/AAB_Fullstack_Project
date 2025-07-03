package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.TileNameAndImage;
import com.aabuilders.Dashboard.Repository.TileNameAndImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TileNameAndImageService {
    @Autowired
    private TileNameAndImageRepository repository;

    public TileNameAndImage saveTileData(String tileName, String tileSize, byte[] image) {
        TileNameAndImage tile = new TileNameAndImage();
        tile.setTileName(tileName);
        tile.setTileSize(tileSize);
        tile.setImage(image);
        return repository.save(tile);
    }

    public List<TileNameAndImage> getAllTiles() {
        return repository.findAll();
    }

    public TileNameAndImage updateTileData(Long id, String tileName, String tileSize, byte[] image) {
        TileNameAndImage tile = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tile not found"));

        // Update fields
        tile.setTileName(tileName);
        tile.setTileSize(tileSize);

        // Update the image if a new image is provided
        if (image != null) {
            tile.setImage(image); // Replace the old image with the new one
        }

        return repository.save(tile);
    }

    public void deleteTileData(Long id) {
        // Implement the logic to delete a tile from the database using the ID
        repository.deleteById(id);
    }
    public void deleteAllTileData() {
        repository.deleteAll();
    }

    public void saveAll(List<TileNameAndImage> tileDataList) {
        repository.saveAll(tileDataList);
    }
}
