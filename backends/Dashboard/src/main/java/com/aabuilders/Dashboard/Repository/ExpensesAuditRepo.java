package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.ExpensesAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesAuditRepo extends JpaRepository<ExpensesAudit, Long> {
    List<ExpensesAudit> findAllByExpenseId(Long expenseId);
}
