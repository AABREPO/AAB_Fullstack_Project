package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.DTO.SiteDTO;
import com.aabuilders.Dashboard.Entity.ExpensesDropdown;
import com.aabuilders.Dashboard.Repository.ExpensesDropdownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensesDropdownService {

    @Autowired
    private ExpensesDropdownRepository repository;

    public List<SiteDTO> getAllSiteNames() {
        return repository.findDistinctSiteNamesWithSerial();
    }

    public List<String> getAllVendors() {
        return repository.findDistinctVendors();
    }

    public List<String> getAllContractors() {
        return repository.findDistinctContractors();
    }

    public List<String> getAllCategories() {
        return repository.findDistinctCategories();
    }

    public List<String> getAllMachineTools() {
        return repository.findDistinctMachineTools();
    }

    public ExpensesDropdown saveSiteName(Long sNo, String siteName) {
        ExpensesDropdown expenseDropdown = new ExpensesDropdown();
        expenseDropdown.setsNo(sNo);  // Set the S.No
        expenseDropdown.setSiteName(siteName);  // Set the site name
        return repository.save(expenseDropdown);  // Save the entity in the database
    }


    public ExpensesDropdown saveVendor(String vendor) {
        ExpensesDropdown expenseDropdown = new ExpensesDropdown();
        expenseDropdown.setVendor(vendor);
        return repository.save(expenseDropdown);
    }

    public ExpensesDropdown saveContractor(String contractor) {
        ExpensesDropdown expenseDropdown = new ExpensesDropdown();
        expenseDropdown.setContractor(contractor);
        return repository.save(expenseDropdown);
    }

    public ExpensesDropdown saveCategory(String category) {
        ExpensesDropdown expenseDropdown = new ExpensesDropdown();
        expenseDropdown.setCategory(category);
        return repository.save(expenseDropdown);
    }

    public ExpensesDropdown saveMachineTools(String machineTools) {
        ExpensesDropdown expenseDropdown = new ExpensesDropdown();
        expenseDropdown.setMachineTools(machineTools);
        return repository.save(expenseDropdown);
    }
}
