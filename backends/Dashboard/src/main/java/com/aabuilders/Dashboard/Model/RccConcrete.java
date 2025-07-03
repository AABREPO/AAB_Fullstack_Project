package com.aabuilders.Dashboard.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RccConcrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String size;
    private String rccTypes;
    private String length;
    private String breadth;
    private String height;
    private String mix;
    private String cement;
    private String sand;
    private String jally;
    private String totalVolume;
    private String labourRate;
    private String totalAmountCft;
    private String labourAmount;
    private String totalAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRccTypes() {
        return rccTypes;
    }

    public void setRccTypes(String rccTypes) {
        this.rccTypes = rccTypes;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getBreadth() {
        return breadth;
    }

    public void setBreadth(String breadth) {
        this.breadth = breadth;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMix() {
        return mix;
    }

    public void setMix(String mix) {
        this.mix = mix;
    }

    public String getCement() {
        return cement;
    }

    public void setCement(String cement) {
        this.cement = cement;
    }

    public String getSand() {
        return sand;
    }

    public void setSand(String sand) {
        this.sand = sand;
    }

    public String getJally() {
        return jally;
    }

    public void setJally(String jally) {
        this.jally = jally;
    }

    public String getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(String totalVolume) {
        this.totalVolume = totalVolume;
    }

    public String getLabourRate() {
        return labourRate;
    }

    public void setLabourRate(String labourRate) {
        this.labourRate = labourRate;
    }

    public String getTotalAmountCft() {
        return totalAmountCft;
    }

    public void setTotalAmountCft(String totalAmountCft) {
        this.totalAmountCft = totalAmountCft;
    }

    public String getLabourAmount() {
        return labourAmount;
    }

    public void setLabourAmount(String labourAmount) {
        this.labourAmount = labourAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}
