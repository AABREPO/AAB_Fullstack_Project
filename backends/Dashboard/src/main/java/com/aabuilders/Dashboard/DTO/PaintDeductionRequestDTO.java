package com.aabuilders.Dashboard.DTO;

import com.aabuilders.Dashboard.Entity.PaintDeductionData;
import java.util.List;

public class PaintDeductionRequestDTO {
    private String clientName;
    private String fileName;
    private String date; // In ISO-8601 format
    private List<PaintDeductionData> data; // List of deduction data items

    // Getters and Setters

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
