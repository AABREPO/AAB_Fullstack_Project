package com.aabuilders.Dashboard.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientName;
    private String fileName;
    private String date;
    private String skirting;
    private String commonWastage;
    private String rate;
    private String commonVendors;
    @JsonProperty("ENo")
    private String ENo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "calculation_id")
    private List<FloorCalculation> calculations;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getSkirting() {
        return skirting;
    }
    public void setSkirting(String skirting) {
        this.skirting = skirting;
    }
    public String getCommonWastage() {
        return commonWastage;
    }
    public void setCommonWastage(String commonWastage) {
        this.commonWastage = commonWastage;
    }
    public String getRate() {
        return rate;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getCommonVendors() {
        return commonVendors;
    }
    public void setCommonVendors(String commonVendors) {
        this.commonVendors = commonVendors;
    }
    public List<FloorCalculation> getCalculations() { return calculations; }
    public void setCalculations(List<FloorCalculation> calculations) { this.calculations = calculations; }
    public String getENo() {
        return ENo;
    }
    public void setENo(String ENo) {
        this.ENo = ENo;
    }
}
