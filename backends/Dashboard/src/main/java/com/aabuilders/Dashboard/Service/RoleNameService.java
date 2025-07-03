package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.RoleNames;
import com.aabuilders.Dashboard.Repository.RoleNamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleNameService {
    @Autowired
    private RoleNamesRepository roleNamesRepository;

    public RoleNames saveRoleNames(RoleNames roleNames){
        return roleNamesRepository.save(roleNames);
    }
    public List<RoleNames> getAllRoles(){
        return  roleNamesRepository.findAll();
    }
}
