package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.PaintType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintTypeRepository extends JpaRepository<PaintType, Long> {
}
