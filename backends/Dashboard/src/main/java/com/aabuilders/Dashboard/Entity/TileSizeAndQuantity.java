package com.aabuilders.Dashboard.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TileSizeAndQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String tileSize;
    private String quantityBox;
    private String areaTile;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTileSize() {
        return tileSize;
    }

    public void setTileSize(String tileSize) {
        this.tileSize = tileSize;
    }

    public String getQuantityBox() {
        return quantityBox;
    }

    public void setQuantityBox(String quantityBox) {
        this.quantityBox = quantityBox;
    }

    public String getAreaTile() {
        return areaTile;
    }

    public void setAreaTile(String areaTile) {
        this.areaTile = areaTile;
    }
}
