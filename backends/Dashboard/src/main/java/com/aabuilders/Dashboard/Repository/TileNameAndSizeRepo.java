package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.TileNameAndSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileNameAndSizeRepo extends JpaRepository<TileNameAndSize,Long> {
}
