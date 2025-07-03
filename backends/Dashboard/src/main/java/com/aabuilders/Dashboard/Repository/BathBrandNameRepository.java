package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.BathBrandNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BathBrandNameRepository extends JpaRepository<BathBrandNames,Long> {
}
