package com.aabuilders.Dashboard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BathFixtureCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String fileName;
    private String date;
    @JsonProperty("ENo")
    private String ENo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "bath_fixture_calculation_id")
    private List<BathFixtureFloorCalculation> bathFixtureCalculations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getENo() {
        return ENo;
    }

    public void setENo(String ENo) {
        this.ENo = ENo;
    }

    public List<BathFixtureFloorCalculation> getBathFixtureCalculations() {
        return bathFixtureCalculations;
    }

    public void setBathFixtureCalculations(List<BathFixtureFloorCalculation> bathFixtureCalculations) {
        this.bathFixtureCalculations = bathFixtureCalculations;
    }
}
