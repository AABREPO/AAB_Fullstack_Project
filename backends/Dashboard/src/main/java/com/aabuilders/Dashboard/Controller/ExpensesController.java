package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.DTO.ExpensesEdit;
import com.aabuilders.Dashboard.DTO.ExpensesAuditDto;
import com.aabuilders.Dashboard.DTO.ExpensesFormDto;
import com.aabuilders.Dashboard.Entity.ExpensesAudit;
import com.aabuilders.Dashboard.Entity.ExpensesForm;
import com.aabuilders.Dashboard.Service.ExpensesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/expenses_form")
public class ExpensesController {
    @Autowired
    private ExpensesServices expensesServices;
    @PostMapping("/save")
    public ResponseEntity<String> addExpensesFormEntry(@RequestBody ExpensesFormDto expensesFormDto) {
        try {
            ExpensesForm expensesForm = new ExpensesForm();
            expensesForm.setAccountType(expensesFormDto.getAccountType());
            expensesForm.setENo(expensesFormDto.getENo());
            expensesForm.setTimestamp(LocalDateTime.now()); // Set the current date and time
            expensesForm.setDate(expensesFormDto.getDate());
            expensesForm.setSiteName(expensesFormDto.getSiteName());
            expensesForm.setVendor(expensesFormDto.getVendor());
            expensesForm.setQuantity(expensesFormDto.getQuantity());
            expensesForm.setContractor(expensesFormDto.getContractor());
            expensesForm.setAmount(expensesFormDto.getAmount());
            expensesForm.setCategory(expensesFormDto.getCategory());
            expensesForm.setComments(expensesFormDto.getComments());
            expensesForm.setMachineTools(expensesFormDto.getMachineTools());
            expensesForm.setBillCopy(expensesFormDto.getBillCopyUrl());

            expensesServices.saveForm(expensesForm);
            return ResponseEntity.status(HttpStatus.CREATED).body("Expenses Form submitted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data format or other error");
        }
    }
    @GetMapping("/get_form")
    public ResponseEntity<List<ExpensesForm>> getAllExpensesFormEntry() {
        List<ExpensesForm> expensesFormsEntries = expensesServices.getAllEntries();
        return ResponseEntity.ok().body(expensesFormsEntries);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateExpense(
            @PathVariable Long id,
            @RequestBody ExpensesEdit expensesEdit
    ) {
        boolean isUpdated = expensesServices.updateExpense(id, expensesEdit); // ✅ only id and expensesEdit
        if (isUpdated) {
            return ResponseEntity.ok("Expense updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
        }
    }
    @GetMapping("/audit/{id}")
    public ResponseEntity<List<ExpensesAuditDto>> getExpenseAudit(@PathVariable Long id) {
        List<ExpensesAudit> audits = expensesServices.getAllAuditsByExpenseId(id);
        List<ExpensesAuditDto> auditDtos = audits.stream().map(audit -> {
            ExpensesAuditDto dto = new ExpensesAuditDto();
            dto.setId(audit.getId());
            dto.setExpenseId(audit.getExpenseId());
            dto.setEditedBy(audit.getEditedBy());
            dto.setEditedDate(audit.getEditedDate());
            dto.setOldSiteName(audit.getOldSiteName());
            dto.setNewSiteName(audit.getNewSiteName());
            dto.setOldVendor(audit.getOldVendor());
            dto.setNewVendor(audit.getNewVendor());
            dto.setOldContractor(audit.getOldContractor());
            dto.setNewContractor(audit.getNewContractor());
            dto.setOldDate(audit.getOldDate());
            dto.setNewDate(audit.getNewDate());
            dto.setOldAccountType(audit.getOldAccountType());
            dto.setNewAccountType(audit.getNewAccountType());
            dto.setOldQuantity(audit.getOldQuantity());
            dto.setNewQuantity(audit.getNewQuantity());
            dto.setOldAmount(audit.getOldAmount());
            dto.setNewAmount(audit.getNewAmount());
            dto.setOldCategory(audit.getOldCategory());
            dto.setNewCategory(audit.getNewCategory());
            dto.setOldComments(audit.getOldComments());
            dto.setNewComments(audit.getNewComments());
            dto.setOldMachineTools(audit.getOldMachineTools());
            dto.setNewMachineTools(audit.getNewMachineTools());
            dto.setOldBillCopy(audit.getOldBillCopy());
            dto.setNewBillCopy(audit.getNewBillCopy());
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(auditDtos);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> clearExpense(@PathVariable Long id, @RequestParam String editedBy) {
        boolean result = expensesServices.clearExpenseData(id, editedBy);
        if (result) {
            return ResponseEntity.ok("Expense data cleared successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found.");
        }
    }
}
