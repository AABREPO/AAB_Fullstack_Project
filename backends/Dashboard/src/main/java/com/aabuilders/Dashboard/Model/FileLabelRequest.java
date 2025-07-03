package com.aabuilders.Dashboard.Model;

public class FileLabelRequest {
    private String fileLabel;
    private String fileType;
    private String clientId;

    // Default constructor
    public FileLabelRequest() {}
    public FileLabelRequest(String fileLabel, String fileType, String clientId) {
        this.fileLabel = fileLabel;
        this.fileType = fileType;
        this.clientId = clientId;
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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
