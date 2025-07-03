package com.example.Dashboard2.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vendorName;
    private String clientName;
    private String date;
    private String siteIncharge;
    private String siteInchargeMobileNumber;
    @JsonProperty("ENo")
    private String ENo;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_order_id")
    private List<PurchaseOrderTable> purchaseTable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSiteIncharge() {
        return siteIncharge;
    }

    public void setSiteIncharge(String siteIncharge) {
        this.siteIncharge = siteIncharge;
    }

    public String getSiteInchargeMobileNumber() {
        return siteInchargeMobileNumber;
    }

    public void setSiteInchargeMobileNumber(String siteInchargeMobileNumber) {
        this.siteInchargeMobileNumber = siteInchargeMobileNumber;
    }

    public String getENo() {
        return ENo;
    }

    public void setENo(String ENo) {
        this.ENo = ENo;
    }

    public List<PurchaseOrderTable> getPurchaseTable() {
        return purchaseTable;
    }

    public void setPurchaseTable(List<PurchaseOrderTable> purchaseTable) {
        this.purchaseTable = purchaseTable;
    }
}
