package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.ExpensesForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpensesRepo extends JpaRepository<ExpensesForm, Long> {
    @Query("SELECT e FROM ExpensesForm e WHERE FUNCTION('DATE', e.timestamp) = CURRENT_DATE")
    List<ExpensesForm> findTodayEntries();
    @Query("SELECT e FROM ExpensesForm e WHERE FUNCTION('DATE', e.timestamp) = :date")
    List<ExpensesForm> findBySpecificDate(@Param("date") LocalDate date);
    List<ExpensesForm> findBySiteName(String siteName);
    List<ExpensesForm> findByVendor(String vendor);
}
