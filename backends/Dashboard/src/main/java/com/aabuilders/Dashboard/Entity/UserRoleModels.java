package com.aabuilders.Dashboard.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class UserRoleModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String models;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_role_Models_id")
    private List<UserRoleModelPermissions> permissions;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public List<UserRoleModelPermissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<UserRoleModelPermissions> permissions) {
        this.permissions = permissions;
    }
}
