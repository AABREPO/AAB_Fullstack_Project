package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.TileAreaName;
import com.aabuilders.Dashboard.Repository.TileAreaNameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TileAreaNameService {
    @Autowired
    private TileAreaNameRepository tileAreaNameRepository;
    public TileAreaName saveTileAreaName(TileAreaName tileAreaName) {
        return tileAreaNameRepository.save(tileAreaName);
    }
    public List<TileAreaName> getAllTileAreaNames() {
        return tileAreaNameRepository.findAll();
    }

    public TileAreaName updateTileAreaName(Long id, TileAreaName tileAreaName) {
        Optional<TileAreaName> existingTileAreaName = tileAreaNameRepository.findById(id);

        if (existingTileAreaName.isPresent()) {
            TileAreaName updatedTileAreaName = existingTileAreaName.get();
            updatedTileAreaName.setAreaName(tileAreaName.getAreaName());
            return tileAreaNameRepository.save(updatedTileAreaName);
        } else {
            throw new RuntimeException("TileAreaName with id " + id + " not found");
        }
    }
    public void deleteTileAreaName(Long id) {
        if (tileAreaNameRepository.existsById(id)) {
            tileAreaNameRepository.deleteById(id);
        } else {
            throw new RuntimeException("TileAreaName with id " + id + " not found");
        }
    }
    @Transactional
    public List<TileAreaName> saveAll(List<TileAreaName> tileAreaNames) {
        return tileAreaNameRepository.saveAll(tileAreaNames);
    }
    @Transactional
    public void deleteAllTileAreaNames() {
        tileAreaNameRepository.deleteAll();
    }
}
