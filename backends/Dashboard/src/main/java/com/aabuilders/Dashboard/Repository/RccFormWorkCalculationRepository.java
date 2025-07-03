package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Model.RccFormWorkCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RccFormWorkCalculationRepository extends JpaRepository<RccFormWorkCalculation,Long> {
}
