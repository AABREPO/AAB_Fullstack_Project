package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.MachineTools;
import com.aabuilders.Dashboard.Entity.VendorNames;
import com.aabuilders.Dashboard.Repository.MachineToolsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class MachineToolsService {

    @Autowired
    private MachineToolsRepository machineToolsRepository;

    public MachineTools saveMachineTools(MachineTools machineTools){
        return machineToolsRepository.save(machineTools);
    }
    public List<MachineTools> getAllMachineTools(){
        return machineToolsRepository.findAll();
    }
    public MachineTools updateMachineTools(Long id, MachineTools machineTools){
        Optional<MachineTools> existingMachineTools = machineToolsRepository.findById(id);
        if (existingMachineTools.isPresent()){
            MachineTools updatedMachineTools = existingMachineTools.get();
            updatedMachineTools.setMachineTool(machineTools.getMachineTool());
            return machineToolsRepository.save(updatedMachineTools);
        } else {
            throw new RuntimeException("Machine Tools not found " + id);
        }
    }
    public String UploadMachineToolsData(MultipartFile file){
        if (file.isEmpty()){
            return "File is empty. please upload a valid SQL file";
        }
        List<MachineTools> machineTools = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))){
            String fileName = file.getOriginalFilename();
            String line;
            if(fileName !=null && fileName.endsWith(".sql")){
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if (line.startsWith("INSERT INTO `machine_tools`")){
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
                            if (dataMap.containsKey("machine_tool")) {
                                MachineTools machineTools1 = new MachineTools();
                                machineTools1.setMachineTool(dataMap.get("machine_tool"));
                                machineTools.add(machineTools1);
                            }
                        }
                    }
                }
            }else {
                return "Unsupported file type";
            }
            if (machineTools.isEmpty()){
                return "No valid";
            }
            machineToolsRepository.saveAll(machineTools);
            return "File uploaded successfully";
        } catch (Exception e){
            e.printStackTrace();
            return "Failed to upload file" + e.getMessage();
        }
    }
    public void deleteMachineTools(Long id){
        if(machineToolsRepository.existsById(id)){
            machineToolsRepository.deleteById(id);
        } else {
            throw new RuntimeException("machine tools id not found" + id);
        }
    }
    @Transactional
    public void deleteAllMachineTools(){
        machineToolsRepository.deleteAll();
    }
}
