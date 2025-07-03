package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.DTO.TileNameAndSizeEdit;
import com.aabuilders.Dashboard.Entity.TileNameAndSize;
import com.aabuilders.Dashboard.Repository.TileNameAndSizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TileNameAndSizeService {

    @Autowired
    private TileNameAndSizeRepo tileNameAndSizeRepo;

    public TileNameAndSize saveTile(TileNameAndSize tileNameAndSize) {
        return tileNameAndSizeRepo.save(tileNameAndSize);
    }

    public List<TileNameAndSize> getAllTiles() {
        return tileNameAndSizeRepo.findAll();
    }

    public boolean updateTile(Long id, TileNameAndSizeEdit tileEdit) {
        Optional<TileNameAndSize> optionalTile = tileNameAndSizeRepo.findById(id);
        if (optionalTile.isPresent()) {
            TileNameAndSize existingTile = optionalTile.get();
            existingTile.setTileName(tileEdit.getTileName());
            existingTile.setTileSize(tileEdit.getTileSize());
            existingTile.setTileImage(tileEdit.getTileImage());
            tileNameAndSizeRepo.save(existingTile);
            return true;
        } else {
            return false;
        }
    }

}
