package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.VendorNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorNameRepository extends JpaRepository<VendorNames, Long> {
}
