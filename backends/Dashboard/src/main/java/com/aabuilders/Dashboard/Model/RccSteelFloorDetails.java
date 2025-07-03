package com.aabuilders.Dashboard.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RccSteelFloorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String floorName;
    private String beamName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "steel_floor_id")
    private List<RccSteel> rccSteels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getBeamName() {
        return beamName;
    }

    public void setBeamName(String beamName) {
        this.beamName = beamName;
    }

    public List<RccSteel> getRccSteels() {
        return rccSteels;
    }

    public void setRccSteels(List<RccSteel> rccSteels) {
        this.rccSteels = rccSteels;
    }
}
