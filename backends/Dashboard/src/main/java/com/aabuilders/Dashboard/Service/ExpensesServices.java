package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.DTO.ExpensesEdit;
import com.aabuilders.Dashboard.Entity.ExpensesAudit;
import com.aabuilders.Dashboard.Entity.ExpensesForm;

import java.util.List;

public interface ExpensesServices {
    ExpensesForm saveForm(ExpensesForm expensesForm);
    List<ExpensesForm> getAllEntries();
    boolean updateExpense(Long id, ExpensesEdit expensesEdit);
    List<ExpensesAudit> getAllAuditsByExpenseId(Long expenseId);
    boolean clearExpenseData(Long id, String editedBy);
}
