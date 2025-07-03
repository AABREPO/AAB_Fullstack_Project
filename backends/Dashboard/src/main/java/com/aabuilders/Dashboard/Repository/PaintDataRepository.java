package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.PaintData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintDataRepository extends JpaRepository<PaintData, Long> {
}
