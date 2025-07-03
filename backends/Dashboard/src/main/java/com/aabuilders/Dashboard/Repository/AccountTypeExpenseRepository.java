package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.AccountTypeExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeExpenseRepository extends JpaRepository<AccountTypeExpense, Long> {
}
