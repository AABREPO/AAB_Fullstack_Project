package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.AccountTypeExpense;
import com.aabuilders.Dashboard.Entity.DailyChecklistEntry;
import com.aabuilders.Dashboard.Repository.AccountTypeExpenseRepository;
import com.aabuilders.Dashboard.Repository.DailyChecklistEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class AccountTypeExpenseService {

    @Autowired
    private AccountTypeExpenseRepository accountTypeExpenseRepository;
    @Autowired
    private DailyChecklistEntryRepo dailyChecklistEntryRepo;

    public AccountTypeExpense saveAccountType(AccountTypeExpense accountTypeExpense){
        return accountTypeExpenseRepository.save(accountTypeExpense);
    }
    public List<AccountTypeExpense> getAllAccountTypes(){
        return accountTypeExpenseRepository.findAll();
    }

    public AccountTypeExpense updateAccountTypeExpenses(Long id, AccountTypeExpense accountTypeExpense){
        Optional<AccountTypeExpense> existingAccountTypeExpense = accountTypeExpenseRepository.findById(id);
        if(existingAccountTypeExpense.isPresent()){
            AccountTypeExpense updatedAccountTypes = existingAccountTypeExpense.get();
            updatedAccountTypes.setAccountType(accountTypeExpense.getAccountType());
            return accountTypeExpenseRepository.save(updatedAccountTypes);
        } else {
            throw new RuntimeException("Account Type not found" + id);
        }
    }
    public String UploadAccountTypeData(MultipartFile file){
        if (file.isEmpty()){
            return "File is empty. please upload a valid SQL file";
        }
        List<AccountTypeExpense> accountTypeExpenses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            String fileName = file.getOriginalFilename();
            String line;
            if(fileName !=null && fileName.endsWith(".sql")){
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `account_type_expense`")){
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
                            if (dataMap.containsKey("account_type")) {
                                AccountTypeExpense accountTypeExpense = new AccountTypeExpense();
                                accountTypeExpense.setAccountType(dataMap.get("account_type"));
                                accountTypeExpenses.add(accountTypeExpense);
                            }
                        }
                    }
                }
            }else {
                return "Unsupported file type";
            }
            if (accountTypeExpenses.isEmpty()){
                return "No valid";
            }
            accountTypeExpenseRepository.saveAll(accountTypeExpenses);
            return "File uploaded successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed to upload file" + e.getMessage();
        }
    }
    public void deleteAccountType(Long id){
        if(accountTypeExpenseRepository.existsById(id)){
            accountTypeExpenseRepository.deleteById(id);
        } else {
            throw new RuntimeException("account type id not found"+ id);
        }
    }
    public List<DailyChecklistEntry> getAllDailyCheckListEntries(){
        return dailyChecklistEntryRepo.findAll();
    }
    @Transactional
    public void deleteAllAccountTypes(){
        accountTypeExpenseRepository.deleteAll();
    }
}
