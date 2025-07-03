package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.TileSizeAndQuantity;
import com.aabuilders.Dashboard.Repository.TileSizeAndQuantityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TileSizeAndQuantityService {
    @Autowired
    private TileSizeAndQuantityRepository tileSizeAndQuantityRepository;
    public TileSizeAndQuantity saveTileSizeAndQuantity(TileSizeAndQuantity tileSizeAndQuantity){
        return tileSizeAndQuantityRepository.save(tileSizeAndQuantity);
    }

    public List<TileSizeAndQuantity> getAllTileSizeAndQuantity(){ return tileSizeAndQuantityRepository.findAll();}

    public TileSizeAndQuantity updateTileSizeAndQuantity(Long id, TileSizeAndQuantity tileSizeAndQuantity) {
        Optional<TileSizeAndQuantity> existingTileSizeAndQuantity = tileSizeAndQuantityRepository.findById(id);
        if (existingTileSizeAndQuantity.isPresent()) {
            TileSizeAndQuantity updatedTileSizeAndQuantity = existingTileSizeAndQuantity.get();
            updatedTileSizeAndQuantity.setTileSize(tileSizeAndQuantity.getTileSize());
            updatedTileSizeAndQuantity.setQuantityBox(tileSizeAndQuantity.getQuantityBox());
            updatedTileSizeAndQuantity.setAreaTile(tileSizeAndQuantity.getAreaTile());
            return tileSizeAndQuantityRepository.save(updatedTileSizeAndQuantity);
        }
        return null;
    }
    public void deleteTileSizeAndQuantity(Long id) {
        if (tileSizeAndQuantityRepository.existsById(id)) {
            tileSizeAndQuantityRepository.deleteById(id);
        } else {
            throw new RuntimeException("TileSizeAndQuantity with id " + id + " not found");
        }
    }
    @Transactional
    public List<TileSizeAndQuantity> saveAll(List<TileSizeAndQuantity> tileSizeAndQuantities) {
        return tileSizeAndQuantityRepository.saveAll(tileSizeAndQuantities);
    }
    @Transactional
    public void deleteAllTileSizeAndQuantities() {
        tileSizeAndQuantityRepository.deleteAll();
    }
}
