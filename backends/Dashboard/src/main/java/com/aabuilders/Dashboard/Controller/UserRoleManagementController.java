package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.UserRolesManagement;
import com.aabuilders.Dashboard.Service.UserRoleManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_roles")
public class UserRoleManagementController {
    @Autowired
    private UserRoleManagementService userRoleManagementService;
    // Create
    @PostMapping("/save")
    public ResponseEntity<UserRolesManagement> createRole(@RequestBody UserRolesManagement userRolesManagement) {
        UserRolesManagement saved = userRoleManagementService.saveUserRolesManagement(userRolesManagement);
        return ResponseEntity.ok(saved);
    }
    // Read all
    @GetMapping("/all")
    public ResponseEntity<List<UserRolesManagement>> getAllRoles() {
        List<UserRolesManagement> roles = userRoleManagementService.getAllUserRoleManagement();
        return ResponseEntity.ok(roles);
    }
    // Update
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody UserRolesManagement userRolesManagement) {
        try {
            UserRolesManagement updated = userRoleManagementService.updateUserRoleManagement(id, userRolesManagement);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Update failed: " + e.getMessage());
        }
    }

}
