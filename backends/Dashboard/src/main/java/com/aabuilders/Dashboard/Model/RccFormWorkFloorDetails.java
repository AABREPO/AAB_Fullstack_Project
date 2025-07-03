package com.aabuilders.Dashboard.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RccFormWorkFloorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String floorName;
    private String beamName;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "form_work_floor_id")
    private List<RccFormWork> rccFormWorks;

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

    public List<RccFormWork> getRccFormWorks() {
        return rccFormWorks;
    }

    public void setRccFormWorks(List<RccFormWork> rccFormWorks) {
        this.rccFormWorks = rccFormWorks;
    }
}
