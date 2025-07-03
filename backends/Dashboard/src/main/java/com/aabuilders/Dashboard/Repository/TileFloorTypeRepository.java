package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.TileFloorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileFloorTypeRepository extends JpaRepository<TileFloorType, Long> {
}
