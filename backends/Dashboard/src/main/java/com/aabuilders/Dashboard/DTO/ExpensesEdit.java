package com.aabuilders.Dashboard.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExpensesEdit {

    private Long Id;
    private Long ENo;
    private String accountType;
    private LocalDateTime timestamp;
    private LocalDate date;
    private String siteName;
    private String vendor;
    private String quantity;
    private String contractor;
    private int amount;
    private String category;
   private String Comments;
    private String machineTools;
    private String billCopy;
    private String editedBy;
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getENo() {
        return ENo;
    }

    public void setENo(Long ENo) {
        this.ENo = ENo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }

    public String getMachineTools() {
        return machineTools;
    }

    public void setMachineTools(String machineTools) {
        this.machineTools = machineTools;
    }

    public String getBillCopy() {
        return billCopy;
    }

    public void setBillCopy(String billCopy) {
        this.billCopy = billCopy;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }
}
