package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.TileVendors;
import com.aabuilders.Dashboard.Repository.TileVendorsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TileVendorService {
    @Autowired
    private TileVendorsRepository tileVendorsRepository;
    public TileVendors saveTileVendors(TileVendors tileVendors){
        return tileVendorsRepository.save(tileVendors);
    }
    public List<TileVendors> getAllTileVendors(){
        return tileVendorsRepository.findAll();
    }
    public TileVendors updateTileVendors(Long id, TileVendors tileVendors){
        Optional<TileVendors> existingTileVendors = tileVendorsRepository.findById(id);
        if(existingTileVendors.isPresent()){
            TileVendors updatedTileVendors = existingTileVendors.get();
            updatedTileVendors.setTileVendor(tileVendors.getTileVendor());
            return tileVendorsRepository.save(updatedTileVendors);
        } else {
            throw new RuntimeException("TileVendor with id" + id + " not found");
        }
    }
    public void deleteTileVendors(Long id){
        if(tileVendorsRepository.existsById(id)){
            tileVendorsRepository.deleteById(id);
        } else {
            throw new RuntimeException("TileVendor with id"+id+" not found");
        }
    }
    @Transactional
    public List<TileVendors> saveAll(List<TileVendors> tileVendors){
        return tileVendorsRepository.saveAll(tileVendors);
    }
    @Transactional
    public void deleteAllTileVendors(){
        tileVendorsRepository.deleteAll();
    }
}
