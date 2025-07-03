package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.TileFloorName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileFloorNameRepository extends JpaRepository<TileFloorName, Long> {
}
