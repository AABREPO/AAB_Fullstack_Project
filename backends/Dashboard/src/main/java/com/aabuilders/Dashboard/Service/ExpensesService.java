package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.DTO.ExpensesEdit;
import com.aabuilders.Dashboard.Entity.ExpensesAudit;
import com.aabuilders.Dashboard.Entity.ExpensesForm;
import com.aabuilders.Dashboard.Repository.ExpensesAuditRepo;
import com.aabuilders.Dashboard.Repository.ExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpensesService implements ExpensesServices {

    @Autowired
    private ExpensesRepo expensesRepo;

    @Autowired
    private ExpensesAuditRepo expensesAuditRepo;


    @Override
    public ExpensesForm saveForm(ExpensesForm expensesForm) {
        return expensesRepo.save(expensesForm);
    }

    @Override
    public List<ExpensesForm> getAllEntries() {
        return expensesRepo.findAll();
    }

    @Override
    public boolean updateExpense(Long id, ExpensesEdit expensesEdit) {
        Optional<ExpensesForm> optionalExpense = expensesRepo.findById(id);
        if (optionalExpense.isPresent()) {
            ExpensesForm existingExpense = optionalExpense.get();
            String editedBy = expensesEdit.getEditedBy();
            // Save all changes in one audit record
            saveAudit(existingExpense, expensesEdit, editedBy);
            // Update the actual entity
            existingExpense.setAccountType(expensesEdit.getAccountType());
            existingExpense.setTimestamp(expensesEdit.getTimestamp());
            existingExpense.setDate(expensesEdit.getDate());
            existingExpense.setSiteName(expensesEdit.getSiteName());
            existingExpense.setVendor(expensesEdit.getVendor());
            existingExpense.setQuantity(expensesEdit.getQuantity());
            existingExpense.setContractor(expensesEdit.getContractor());
            existingExpense.setAmount(expensesEdit.getAmount());
            existingExpense.setCategory(expensesEdit.getCategory());
            existingExpense.setComments(expensesEdit.getComments());
            existingExpense.setMachineTools(expensesEdit.getMachineTools());
            existingExpense.setBillCopy(expensesEdit.getBillCopy());

            expensesRepo.save(existingExpense);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public List<ExpensesAudit> getAllAuditsByExpenseId(Long expenseId) {
        return expensesAuditRepo.findAllByExpenseId(expenseId);
    }

    private void saveAudit(ExpensesForm oldData, ExpensesEdit newData, String editedBy) {
        ExpensesAudit audit = new ExpensesAudit();
        audit.setExpenseId(oldData.getId());
        audit.setEditedBy(editedBy);
        audit.setEditedDate(LocalDateTime.now());

        // Save the old and new value for each field, even if it has not changed
        audit.setOldSiteName(oldData.getSiteName());
        audit.setNewSiteName(newData.getSiteName());

        audit.setOldVendor(oldData.getVendor());
        audit.setNewVendor(newData.getVendor());

        audit.setOldContractor(oldData.getContractor());
        audit.setNewContractor(newData.getContractor());

        audit.setOldAmount(oldData.getAmount() != 0 ? String.valueOf(oldData.getAmount()) : null);
        audit.setNewAmount(newData.getAmount() != 0 ? String.valueOf(newData.getAmount()) : null);

        audit.setOldAccountType(oldData.getAccountType());
        audit.setNewAccountType(newData.getAccountType());

        audit.setOldComments(oldData.getComments());
        audit.setNewComments(newData.getComments());

        audit.setOldCategory(oldData.getCategory());
        audit.setNewCategory(newData.getCategory());

        audit.setOldDate(oldData.getDate() != null ? oldData.getDate().toString() : null);
        audit.setNewDate(newData.getDate() != null ? newData.getDate().toString() : null);

        audit.setOldQuantity(oldData.getQuantity());
        audit.setNewQuantity(newData.getQuantity());

        audit.setOldMachineTools(oldData.getMachineTools());
        audit.setNewMachineTools(newData.getMachineTools());

        audit.setOldBillCopy(oldData.getBillCopy());
        audit.setNewBillCopy(newData.getBillCopy());

        // Save the audit entry in the database
        expensesAuditRepo.save(audit);
    }


    @Override
    public boolean clearExpenseData(Long id, String editedBy) {
        Optional<ExpensesForm> optionalExpense = expensesRepo.findById(id);
        if (optionalExpense.isPresent()) {
            ExpensesForm existingExpense = optionalExpense.get();

            ExpensesAudit audit = new ExpensesAudit();
            audit.setExpenseId(existingExpense.getId());
            audit.setEditedBy(editedBy);
            audit.setEditedDate(LocalDateTime.now());

            // Audit + Clear fields
            if (existingExpense.getAccountType() != null) {
                audit.setOldAccountType(existingExpense.getAccountType());
                audit.setNewAccountType(null);
                existingExpense.setAccountType(null);
            }

            if (existingExpense.getDate() != null) {
                audit.setOldDate(existingExpense.getDate().toString());
                audit.setNewDate(null);
                existingExpense.setDate(null);
            }

            if (existingExpense.getSiteName() != null) {
                audit.setOldSiteName(existingExpense.getSiteName());
                audit.setNewSiteName(null);
                existingExpense.setSiteName(null);
            }

            if (existingExpense.getVendor() != null) {
                audit.setOldVendor(existingExpense.getVendor());
                audit.setNewVendor(null);
                existingExpense.setVendor(null);
            }

            if (existingExpense.getQuantity() != null) {
                audit.setOldQuantity(existingExpense.getQuantity());
                audit.setNewQuantity(null);
                existingExpense.setQuantity(null);
            }

            if (existingExpense.getContractor() != null) {
                audit.setOldContractor(existingExpense.getContractor());
                audit.setNewContractor(null);
                existingExpense.setContractor(null);
            }

            if (existingExpense.getAmount() != 0) {
                audit.setOldAmount(String.valueOf(existingExpense.getAmount()));
                audit.setNewAmount("0");
                existingExpense.setAmount(0);
            }

            if (existingExpense.getComments() != null) {
                audit.setOldComments(existingExpense.getComments());
                audit.setNewComments(null);
                existingExpense.setComments(null);
            }

            if (existingExpense.getCategory() != null) {
                audit.setOldCategory(existingExpense.getCategory());
                audit.setNewCategory(null);
                existingExpense.setCategory(null);
            }

            if (existingExpense.getMachineTools() != null) {
                audit.setOldMachineTools(existingExpense.getMachineTools());
                audit.setNewMachineTools(null);
                existingExpense.setMachineTools(null);
            }

            if (existingExpense.getBillCopy() != null) {
                audit.setOldBillCopy(existingExpense.getBillCopy());
                audit.setNewBillCopy(null);
                existingExpense.setBillCopy(null);
            }

            // Save audit and updated expense
            expensesAuditRepo.save(audit);
            expensesRepo.save(existingExpense);
            return true;
        } else {
            return false;
        }
    }

}
