package com.aabuilders.Dashboard.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class PaintCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String fileName;
    private String date;
    private String commonPaint;
    private String commonPaintColor;
    private String commonExteriorPaint;
    private String commonExteriorPaintColor;
    private String commonHeight;
    private String commonPutty;
    private String commonPrimer;
    private String commonWallCoat;
    private String commonCeilingCoat;
    private String commonWaterProof;
    private String commonWastagePercentage;
    private String commonExteriorHeight;
    private String commonExteriorPutty;
    private String commonExteriorPrimer;
    private String commonExteriorWallCoat;
    private String commonExteriorCeilingCoat;
    private String commonExteriorWaterProof;
    private String commonExteriorWastagePercentage;
    @JsonProperty("ENo")
    private String ENo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "paint_calculation_id")
    private List<PaintFloorCalculation> paintCalculations;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getCommonPaint() {
        return commonPaint;
    }

    public void setCommonPaint(String commonPaint) {
        this.commonPaint = commonPaint;
    }

    public String getCommonPaintColor() {
        return commonPaintColor;
    }

    public void setCommonPaintColor(String commonPaintColor) {
        this.commonPaintColor = commonPaintColor;
    }

    public String getCommonExteriorPaint() {
        return commonExteriorPaint;
    }

    public void setCommonExteriorPaint(String commonExteriorPaint) {
        this.commonExteriorPaint = commonExteriorPaint;
    }

    public String getCommonExteriorPaintColor() {
        return commonExteriorPaintColor;
    }

    public void setCommonExteriorPaintColor(String commonExteriorPaintColor) {
        this.commonExteriorPaintColor = commonExteriorPaintColor;
    }

    public String getCommonHeight() {
        return commonHeight;
    }

    public void setCommonHeight(String commonHeight) {
        this.commonHeight = commonHeight;
    }

    public String getCommonPutty() {
        return commonPutty;
    }

    public void setCommonPutty(String commonPutty) {
        this.commonPutty = commonPutty;
    }

    public String getCommonPrimer() {
        return commonPrimer;
    }

    public void setCommonPrimer(String commonPrimer) {
        this.commonPrimer = commonPrimer;
    }

    public String getCommonWallCoat() {
        return commonWallCoat;
    }

    public void setCommonWallCoat(String commonWallCoat) {
        this.commonWallCoat = commonWallCoat;
    }

    public String getCommonCeilingCoat() {
        return commonCeilingCoat;
    }

    public void setCommonCeilingCoat(String commonCeilingCoat) {
        this.commonCeilingCoat = commonCeilingCoat;
    }

    public String getCommonWaterProof() {
        return commonWaterProof;
    }

    public void setCommonWaterProof(String commonWaterProof) {
        this.commonWaterProof = commonWaterProof;
    }

    public String getCommonWastagePercentage() {
        return commonWastagePercentage;
    }

    public void setCommonWastagePercentage(String commonWastagePercentage) {
        this.commonWastagePercentage = commonWastagePercentage;
    }

    public String getCommonExteriorHeight() {
        return commonExteriorHeight;
    }

    public void setCommonExteriorHeight(String commonExteriorHeight) {
        this.commonExteriorHeight = commonExteriorHeight;
    }

    public String getCommonExteriorPutty() {
        return commonExteriorPutty;
    }

    public void setCommonExteriorPutty(String commonExteriorPutty) {
        this.commonExteriorPutty = commonExteriorPutty;
    }

    public String getCommonExteriorPrimer() {
        return commonExteriorPrimer;
    }

    public void setCommonExteriorPrimer(String commonExteriorPrimer) {
        this.commonExteriorPrimer = commonExteriorPrimer;
    }

    public String getCommonExteriorWallCoat() {
        return commonExteriorWallCoat;
    }

    public void setCommonExteriorWallCoat(String commonExteriorWallCoat) {
        this.commonExteriorWallCoat = commonExteriorWallCoat;
    }

    public String getCommonExteriorCeilingCoat() {
        return commonExteriorCeilingCoat;
    }

    public void setCommonExteriorCeilingCoat(String commonExteriorCeilingCoat) {
        this.commonExteriorCeilingCoat = commonExteriorCeilingCoat;
    }

    public String getCommonExteriorWaterProof() {
        return commonExteriorWaterProof;
    }

    public void setCommonExteriorWaterProof(String commonExteriorWaterProof) {
        this.commonExteriorWaterProof = commonExteriorWaterProof;
    }

    public String getCommonExteriorWastagePercentage() {
        return commonExteriorWastagePercentage;
    }

    public void setCommonExteriorWastagePercentage(String commonExteriorWastagePercentage) {
        this.commonExteriorWastagePercentage = commonExteriorWastagePercentage;
    }

    public List<PaintFloorCalculation> getPaintCalculations() { return paintCalculations; }
    public void setPaintCalculations(List<PaintFloorCalculation> paintCalculations) { this.paintCalculations = paintCalculations; }

    public String getENo() { return ENo; }
    public void setENo(String ENo) { this.ENo = ENo; }
}
