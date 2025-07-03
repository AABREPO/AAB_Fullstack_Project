package com.aabuilders.Dashboard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class RccConcreteCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String fileName;
    private String date;
    @JsonProperty("ENo")
    private String ENo;
    private String cementRate;
    private String sandRate;
    private String jallyRate;
    private String cementWastage;
    private String sandWastage;
    private String jallyWastage;
    private String commonMix;
    private String commonLabourRate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "concrete_client_id")
    private List<RccConcreteFloorDetails> rccConcreteFloorDetails;

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

    public String getCementRate() {
        return cementRate;
    }

    public void setCementRate(String cementRate) {
        this.cementRate = cementRate;
    }

    public String getSandRate() {
        return sandRate;
    }

    public void setSandRate(String sandRate) {
        this.sandRate = sandRate;
    }

    public String getJallyRate() {
        return jallyRate;
    }

    public void setJallyRate(String jallyRate) {
        this.jallyRate = jallyRate;
    }

    public String getCementWastage() {
        return cementWastage;
    }

    public void setCementWastage(String cementWastage) {
        this.cementWastage = cementWastage;
    }

    public String getSandWastage() {
        return sandWastage;
    }

    public void setSandWastage(String sandWastage) {
        this.sandWastage = sandWastage;
    }

    public String getJallyWastage() {
        return jallyWastage;
    }

    public void setJallyWastage(String jallyWastage) {
        this.jallyWastage = jallyWastage;
    }

    public String getCommonMix() {
        return commonMix;
    }

    public void setCommonMix(String commonMix) {
        this.commonMix = commonMix;
    }

    public String getCommonLabourRate() {
        return commonLabourRate;
    }

    public void setCommonLabourRate(String commonLabourRate) {
        this.commonLabourRate = commonLabourRate;
    }

    public List<RccConcreteFloorDetails> getRccConcreteFloorDetails() {
        return rccConcreteFloorDetails;
    }

    public void setRccConcreteFloorDetails(List<RccConcreteFloorDetails> rccConcreteFloorDetails) {
        this.rccConcreteFloorDetails = rccConcreteFloorDetails;
    }
}
