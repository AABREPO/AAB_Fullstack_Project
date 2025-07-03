package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.RccBeamName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RccBeamNameRepository extends JpaRepository<RccBeamName,Long> {
}
