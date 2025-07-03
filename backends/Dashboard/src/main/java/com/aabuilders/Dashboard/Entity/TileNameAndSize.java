package com.aabuilders.Dashboard.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TileNameAndSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tileName;
    private String tileSize;
    private String tileImage;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTileName() {
        return tileName;
    }
    public void setTileName(String tileName) {
        this.tileName = tileName;
    }
    public String getTileSize() {
        return tileSize;
    }
    public void setTileSize(String tileSize) {
        this.tileSize = tileSize;
    }

    public String getTileImage() {
        return tileImage;
    }
    public void setTileImage(String tileImage) {
        this.tileImage = tileImage;
    }
}
