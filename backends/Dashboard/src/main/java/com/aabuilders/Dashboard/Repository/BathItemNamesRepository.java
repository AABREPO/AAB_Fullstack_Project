package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.BathItemNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BathItemNamesRepository extends JpaRepository<BathItemNames,Long> {
}
