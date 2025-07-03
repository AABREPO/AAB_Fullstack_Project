package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.UserRolesManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleManagementRepository extends JpaRepository<UserRolesManagement, Long> {
}
