package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.PaintVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintVariantRepository extends JpaRepository<PaintVariant,Long> {
}
