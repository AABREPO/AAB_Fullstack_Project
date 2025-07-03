package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.TileVendors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileVendorsRepository extends JpaRepository<TileVendors, Long> {
}
