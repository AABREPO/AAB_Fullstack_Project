package com.aabuilders.Dashboard.Model;

import jakarta.persistence.*;

@Entity
public class TilePdfLocal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileLabel;

    @Column(nullable = false)
    private String fileType = "CC";

    @Column(nullable = false)
    private int increment = 1; // Starts from 1 and increments

    @Column(nullable = false)
    private String clientId; // Client identifier

    // Constructors, getters, setters
    public TilePdfLocal() {}

    public TilePdfLocal(String fileLabel, String fileType, int increment, String clientId) {
        this.fileLabel = fileLabel;
        this.fileType = fileType;
        this.increment = increment;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileLabel() {
        return fileLabel;
    }

    public void setFileLabel(String fileLabel) {
        this.fileLabel = fileLabel;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
