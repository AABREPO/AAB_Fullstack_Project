package com.aabuilders.Dashboard.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class PaintDeductionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;
    private String fileName;
    private String date; // Stored as a string in ISO-8601 format

    @ElementCollection
    private List<PaintDeductionData> data; // List of deduction data items

    // Getters and Setters

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

    public List<PaintDeductionData> getData() {
        return data;
    }

    public void setData(List<PaintDeductionData> data) {
        this.data = data;
    }
}
