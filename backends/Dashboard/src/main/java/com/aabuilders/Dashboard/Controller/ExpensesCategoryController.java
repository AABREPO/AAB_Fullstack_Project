package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.ExpensesCategory;
import com.aabuilders.Dashboard.Service.ExpensesCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/expenses_categories")
public class ExpensesCategoryController {
    @Autowired
    private ExpensesCategoryService expensesCategoryService;

    @PostMapping("/save")
    public ExpensesCategory saveExpensesCategory(@RequestBody ExpensesCategory expensesCategory){
        return expensesCategoryService.saveExpensesCategory(expensesCategory);
    }
    @GetMapping("/getAll")
    public List<ExpensesCategory> getAllExpensesCategory(){
        return expensesCategoryService.getAllExpensesCategory();
    }
    @PutMapping("/edit/{id}")
    public ExpensesCategory updateCategory(@PathVariable Long id, @RequestBody ExpensesCategory expensesCategory){
        return expensesCategoryService.updateCategoryData(id, expensesCategory);
    }
    @PostMapping("/bulk_upload")
    public String uploadExpensesCategoryData(@RequestParam("file") MultipartFile file){
        return expensesCategoryService.uploadExpensesCategoryData(file);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id){
        expensesCategoryService.deleteCategories(id);
    }
    @DeleteMapping("/deleteAll")
    public String deleteAllExpensesCategory(){
        expensesCategoryService.deleteAllExpensesCategory();
        return "All Contractor Name deleted";
    }
}
