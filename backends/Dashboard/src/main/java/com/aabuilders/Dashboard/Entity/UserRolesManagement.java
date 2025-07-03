package com.aabuilders.Dashboard.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserRolesManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String UserRoles;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_role_id")
    private List<UserRoleModels> userModels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserRoles() {
        return UserRoles;
    }

    public void setUserRoles(String userRoles) {
        UserRoles = userRoles;
    }

    public List<UserRoleModels> getUserModels() {
        return userModels;
    }

    public void setUserModels(List<UserRoleModels> userModels) {
        this.userModels = userModels;
    }
}
