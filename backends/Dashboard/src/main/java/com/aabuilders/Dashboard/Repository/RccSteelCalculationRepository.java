package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Model.RccSteelCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RccSteelCalculationRepository extends JpaRepository<RccSteelCalculation, Long> {
}
