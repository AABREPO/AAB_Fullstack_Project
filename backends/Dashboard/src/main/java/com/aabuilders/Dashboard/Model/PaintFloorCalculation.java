package com.aabuilders.Dashboard.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PaintFloorCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String floorName;
    private String areaName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "paint_floor_id")
    private List<PaintTile> paintTiles;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFloorName() { return floorName; }
    public void setFloorName(String floorName) { this.floorName = floorName; }
    public String getAreaName() { return areaName; }
    public void setAreaName(String areaName) { this.areaName = areaName; }
    public List<PaintTile> getPaintTiles() { return paintTiles; }
    public void setPaintTiles(List<PaintTile> paintTiles) { this.paintTiles = paintTiles; }
}
