package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.RccBeamTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RccBeamTypesRepository extends JpaRepository<RccBeamTypes,Long> {
}
