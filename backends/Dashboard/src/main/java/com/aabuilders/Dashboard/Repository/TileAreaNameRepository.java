package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.TileAreaName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TileAreaNameRepository extends JpaRepository<TileAreaName, Long> {
}
