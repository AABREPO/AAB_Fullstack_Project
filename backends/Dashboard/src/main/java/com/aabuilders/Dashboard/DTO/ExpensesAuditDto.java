package com.aabuilders.Dashboard.DTO;

import java.time.LocalDateTime;

public class ExpensesAuditDto {

    private Long id;
    private Long expenseId;
    private String editedBy;
    private LocalDateTime editedDate;
    private String oldSiteName;
    private String newSiteName;
    private String oldVendor;
    private String newVendor;
    private String oldContractor;
    private String newContractor;
    private String oldDate;
    private String newDate;
    private String oldAccountType;
    private String newAccountType;
    private String oldQuantity;
    private String newQuantity;
    private String oldAmount;
    private String newAmount;
    private String oldCategory;
    private String newCategory;
    private String oldComments;
    private String newComments;
    private String oldMachineTools;
    private String newMachineTools;
    private String oldBillCopy;
    private String newBillCopy;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public LocalDateTime getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(LocalDateTime editedDate) {
        this.editedDate = editedDate;
    }

    public String getOldSiteName() {
        return oldSiteName;
    }

    public void setOldSiteName(String oldSiteName) {
        this.oldSiteName = oldSiteName;
    }

    public String getNewSiteName() {
        return newSiteName;
    }

    public void setNewSiteName(String newSiteName) {
        this.newSiteName = newSiteName;
    }

    public String getOldVendor() {
        return oldVendor;
    }

    public void setOldVendor(String oldVendor) {
        this.oldVendor = oldVendor;
    }

    public String getNewVendor() {
        return newVendor;
    }

    public void setNewVendor(String newVendor) {
        this.newVendor = newVendor;
    }

    public String getOldContractor() {
        return oldContractor;
    }

    public void setOldContractor(String oldContractor) {
        this.oldContractor = oldContractor;
    }

    public String getNewContractor() {
        return newContractor;
    }

    public void setNewContractor(String newContractor) {
        this.newContractor = newContractor;
    }

    public String getOldDate() {
        return oldDate;
    }

    public void setOldDate(String oldDate) {
        this.oldDate = oldDate;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getOldAccountType() {
        return oldAccountType;
    }

    public void setOldAccountType(String oldAccountType) {
        this.oldAccountType = oldAccountType;
    }

    public String getNewAccountType() {
        return newAccountType;
    }

    public void setNewAccountType(String newAccountType) {
        this.newAccountType = newAccountType;
    }

    public String getOldQuantity() {
        return oldQuantity;
    }

    public void setOldQuantity(String oldQuantity) {
        this.oldQuantity = oldQuantity;
    }

    public String getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(String newQuantity) {
        this.newQuantity = newQuantity;
    }

    public String getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(String oldAmount) {
        this.oldAmount = oldAmount;
    }

    public String getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(String newAmount) {
        this.newAmount = newAmount;
    }

    public String getOldCategory() {
        return oldCategory;
    }

    public void setOldCategory(String oldCategory) {
        this.oldCategory = oldCategory;
    }

    public String getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(String newCategory) {
        this.newCategory = newCategory;
    }

    public String getOldComments() {
        return oldComments;
    }

    public void setOldComments(String oldComments) {
        this.oldComments = oldComments;
    }

    public String getNewComments() {
        return newComments;
    }

    public void setNewComments(String newComments) {
        this.newComments = newComments;
    }

    public String getOldMachineTools() {
        return oldMachineTools;
    }

    public void setOldMachineTools(String oldMachineTools) {
        this.oldMachineTools = oldMachineTools;
    }

    public String getNewMachineTools() {
        return newMachineTools;
    }

    public void setNewMachineTools(String newMachineTools) {
        this.newMachineTools = newMachineTools;
    }

    public String getOldBillCopy() {
        return oldBillCopy;
    }

    public void setOldBillCopy(String oldBillCopy) {
        this.oldBillCopy = oldBillCopy;
    }

    public String getNewBillCopy() {
        return newBillCopy;
    }

    public void setNewBillCopy(String newBillCopy) {
        this.newBillCopy = newBillCopy;
    }
}
