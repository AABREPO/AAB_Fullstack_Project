package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.BathType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BathTypeRepository extends JpaRepository<BathType,Long> {
}
