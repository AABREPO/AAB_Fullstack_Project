package com.aabuilders.Dashboard.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RccSteel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String size;
    private String rccTypes;
    private double length;
    private double breadth;
    private double height;
    private double mm8;
    private double mm10;
    private double mm12;
    private double mm16;
    private double mm20;
    private double mm25;
    private double mm32;
    private double totalWeight;
    private double rate;
    private double totalAmount;

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

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getBreadth() {
        return breadth;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMm8() {
        return mm8;
    }

    public void setMm8(double mm8) {
        this.mm8 = mm8;
    }

    public double getMm10() {
        return mm10;
    }

    public void setMm10(double mm10) {
        this.mm10 = mm10;
    }

    public double getMm12() {
        return mm12;
    }

    public void setMm12(double mm12) {
        this.mm12 = mm12;
    }

    public double getMm16() {
        return mm16;
    }

    public void setMm16(double mm16) {
        this.mm16 = mm16;
    }

    public double getMm20() {
        return mm20;
    }

    public void setMm20(double mm20) {
        this.mm20 = mm20;
    }

    public double getMm25() {
        return mm25;
    }

    public void setMm25(double mm25) {
        this.mm25 = mm25;
    }

    public double getMm32() {
        return mm32;
    }

    public void setMm32(double mm32) {
        this.mm32 = mm32;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
