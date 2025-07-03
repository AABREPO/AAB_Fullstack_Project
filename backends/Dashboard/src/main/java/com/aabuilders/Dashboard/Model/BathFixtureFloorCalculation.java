package com.aabuilders.Dashboard.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BathFixtureFloorCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String floorName;
    private String areaName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "bath_fixture_floor_id")
    private List<BathFixtureTable> bathFixtureTables;

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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<BathFixtureTable> getBathFixtureTables() {
        return bathFixtureTables;
    }

    public void setBathFixtureTables(List<BathFixtureTable> bathFixtureTables) {
        this.bathFixtureTables = bathFixtureTables;
    }
}
