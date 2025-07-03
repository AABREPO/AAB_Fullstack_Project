package com.aabuilders.Dashboard.Controller;

import com.aabuilders.Dashboard.Entity.RoleNames;
import com.aabuilders.Dashboard.Service.RoleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleNamesController {
    @Autowired
    private RoleNameService roleNameService;

    @PostMapping("/save")
    public RoleNames saveRoleName(@RequestBody RoleNames roleNames){
        return roleNameService.saveRoleNames(roleNames);
    }
    @GetMapping("/all")
    public List<RoleNames> getAllRoleName(){
        return roleNameService.getAllRoles();
    }
}
