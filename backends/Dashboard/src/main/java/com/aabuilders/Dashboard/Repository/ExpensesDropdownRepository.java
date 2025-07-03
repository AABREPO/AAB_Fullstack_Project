package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.DTO.SiteDTO;
import com.aabuilders.Dashboard.Entity.ExpensesDropdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesDropdownRepository extends JpaRepository<ExpensesDropdown, Long> {

    @Query("SELECT new com.aabuilders.Dashboard.DTO.SiteDTO(e.sNo, e.siteName) FROM ExpensesDropdown e WHERE e.siteName IS NOT NULL GROUP BY e.sNo, e.siteName")
    List<SiteDTO> findDistinctSiteNamesWithSerial();

    @Query("SELECT DISTINCT e.vendor FROM ExpensesDropdown e ORDER BY e.vendor ASC")
    List<String> findDistinctVendors();

    @Query("SELECT DISTINCT e.contractor FROM ExpensesDropdown e ORDER BY e.contractor ASC")
    List<String> findDistinctContractors();

    @Query("SELECT DISTINCT e.category FROM ExpensesDropdown e ORDER BY e.category ASC")
    List<String> findDistinctCategories();

    @Query("SELECT DISTINCT e.machineTools FROM ExpensesDropdown e ORDER BY e.machineTools ASC")
    List<String> findDistinctMachineTools();
}
