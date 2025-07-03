package com.aabuilders.Dashboard.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rcc_beam_name")
public class RccBeamName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String beamName;
    private String formula;
    private String rate;
    @Lob
    @Column(name = "measurement_image", columnDefinition = "LONGBLOB")
    private byte[] measurementImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeamName() {
        return beamName;
    }

    public void setBeamName(String beamName) {
        this.beamName = beamName;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public byte[] getMeasurementImage() {
        return measurementImage;
    }

    public void setMeasurementImage(byte[] measurementImage) {
        this.measurementImage = measurementImage;
    }
}
