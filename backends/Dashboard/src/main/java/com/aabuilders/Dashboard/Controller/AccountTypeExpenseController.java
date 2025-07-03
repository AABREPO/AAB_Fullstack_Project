package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.AccountTypeExpense;
import com.aabuilders.Dashboard.Entity.DailyChecklistEntry;
import com.aabuilders.Dashboard.Service.AccountTypeExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/account_type")
public class AccountTypeExpenseController {
    @Autowired
    private AccountTypeExpenseService accountTypeExpenseService;
    @PostMapping("/save")
    public AccountTypeExpense saveAccountType(@RequestBody AccountTypeExpense accountTypeExpense){
        return accountTypeExpenseService.saveAccountType(accountTypeExpense);
    }
    @GetMapping("/getAll")
    public List<AccountTypeExpense> getAllAccountTypes(){
        return accountTypeExpenseService.getAllAccountTypes();
    }

    @GetMapping("/daily_checklist")
    public List<DailyChecklistEntry> getAllDailyCheckList(){
        return accountTypeExpenseService.getAllDailyCheckListEntries();
    }
    @PutMapping("/edit/{id}")
    public AccountTypeExpense updateAccountType(@PathVariable Long id, @RequestBody AccountTypeExpense accountTypeExpense){
        return accountTypeExpenseService.updateAccountTypeExpenses(id, accountTypeExpense);
    }
    @PostMapping("/bulk_Upload")
    public String updateAccountTypesData(@RequestParam("file")MultipartFile file){
        return accountTypeExpenseService.UploadAccountTypeData(file);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllAccountTypes(){
        accountTypeExpenseService.deleteAllAccountTypes();
        return "All Account Type Deleted Successfully!!";
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAccountTypes(@PathVariable Long id){
        accountTypeExpenseService.deleteAccountType(id);
    }
}
