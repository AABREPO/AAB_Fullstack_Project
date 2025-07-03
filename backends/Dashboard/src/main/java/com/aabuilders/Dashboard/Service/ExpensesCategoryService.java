package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.ContractorNames;
import com.aabuilders.Dashboard.Entity.ExpensesCategory;
import com.aabuilders.Dashboard.Repository.ContractorNamesRepository;
import com.aabuilders.Dashboard.Repository.ExpensesCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class ExpensesCategoryService {
    @Autowired
    private ExpensesCategoryRepository expensesCategoryRepository;

    public ExpensesCategory saveExpensesCategory(ExpensesCategory expensesCategory){
        return expensesCategoryRepository.save(expensesCategory);
    }
    public List<ExpensesCategory> getAllExpensesCategory(){
        return expensesCategoryRepository.findAll();
    }
    public ExpensesCategory updateCategoryData(Long id, ExpensesCategory expensesCategory){
        Optional<ExpensesCategory> existingExpensesCategory = expensesCategoryRepository.findById(id);
        if (existingExpensesCategory.isPresent()){
            ExpensesCategory updatedExpensesCategories = existingExpensesCategory.get();
            updatedExpensesCategories.setCategory(expensesCategory.getCategory());
            return expensesCategoryRepository.save(updatedExpensesCategories);
        } else {
            throw new RuntimeException("Categories not found "+ id);
        }
    }
    public String uploadExpensesCategoryData(MultipartFile file){
        if (file.isEmpty()){
            return "File is empty. please upload a valid SQL file";
        }
        List<ExpensesCategory> expensesCategories = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            String fileName = file.getOriginalFilename();
            String line;
            if(fileName !=null && fileName.endsWith(".sql")){
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `expenses_category`")){
                        int startIdx = line.indexOf('(') + 1;
                        int endIdx = line.indexOf(')');
                        String columnsPart = line.substring(startIdx, endIdx).replace("`", "").trim();
                        String[] columns = columnsPart.split(",");
                        String valuesPart = line.substring(line.indexOf("VALUES") + 6).trim();
                        valuesPart = valuesPart.substring(1, valuesPart.length() -1);
                        if (valuesPart.endsWith(")")){
                            valuesPart = valuesPart.substring(0, valuesPart.length() -1);
                        }
                        String[] records = valuesPart.split("\\),\\s*\\(");
                        for (String record : records) {
                            String[] fields = record.replace("'", "").split(",");
                            Map<String, String> dataMap = new HashMap<>();
                            for (int i = 0; i < columns.length && i < fields.length; i++) {
                                dataMap.put(columns[i].trim(), fields[i].trim());
                            }
                            // Check if both site_name and site_no are present
                            if (dataMap.containsKey("category")) {
                                ExpensesCategory expensesCategory = new ExpensesCategory();
                                expensesCategory.setCategory(dataMap.get("category"));
                                expensesCategories.add(expensesCategory);
                            }
                        }
                    }
                }
            }else {
                return "Unsupported file type";
            }
            if (expensesCategories.isEmpty()){
                return "No valid";
            }
            expensesCategoryRepository.saveAll(expensesCategories);
            return "File uploaded successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed to upload file" + e.getMessage();
        }
    }
    @Transactional
    public void deleteAllExpensesCategory(){
        expensesCategoryRepository.deleteAll();
    }
    public void deleteCategories(Long id){
        expensesCategoryRepository.deleteById(id);
    }
}
