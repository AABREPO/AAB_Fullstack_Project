package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.RccSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RccSizeRepository extends JpaRepository<RccSize,Long> {
}
