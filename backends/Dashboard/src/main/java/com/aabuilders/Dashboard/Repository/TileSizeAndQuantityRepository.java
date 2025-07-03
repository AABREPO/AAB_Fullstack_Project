package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.TileSizeAndQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileSizeAndQuantityRepository extends JpaRepository<TileSizeAndQuantity,Long> {
}
