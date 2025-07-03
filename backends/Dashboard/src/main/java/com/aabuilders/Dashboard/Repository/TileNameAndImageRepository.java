package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.TileNameAndImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TileNameAndImageRepository extends JpaRepository<TileNameAndImage, Long> {
}
