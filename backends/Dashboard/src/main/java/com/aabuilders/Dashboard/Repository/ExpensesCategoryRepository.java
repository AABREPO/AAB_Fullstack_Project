package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.ExpensesCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesCategoryRepository extends JpaRepository<ExpensesCategory , Long> {
}
