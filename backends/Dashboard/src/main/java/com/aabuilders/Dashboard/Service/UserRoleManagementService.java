package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.Entity.UserRolesManagement;
import com.aabuilders.Dashboard.Repository.UserRoleManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleManagementService {

    @Autowired
    private UserRoleManagementRepository userRoleManagementRepository;

    public UserRolesManagement saveUserRolesManagement(UserRolesManagement userRolesManagement){
        return userRoleManagementRepository.save(userRolesManagement);
    }
    public List<UserRolesManagement> getAllUserRoleManagement(){
        return userRoleManagementRepository.findAll();
    }
    public UserRolesManagement updateUserRoleManagement(Long id, UserRolesManagement updateUserRoles){
        return userRoleManagementRepository.findById(id)
                .map(existing -> {
                    updateUserRoles.setId(id);
                    return userRoleManagementRepository.save(updateUserRoles);
                })
                .orElseThrow(() -> new RuntimeException("Roles not found with this id:" + id));
    }
}
