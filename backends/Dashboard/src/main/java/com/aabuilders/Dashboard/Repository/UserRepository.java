package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
