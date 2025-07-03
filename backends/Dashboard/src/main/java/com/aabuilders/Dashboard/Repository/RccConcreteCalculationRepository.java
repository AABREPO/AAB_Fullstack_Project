package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Model.RccConcreteCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RccConcreteCalculationRepository extends JpaRepository<RccConcreteCalculation,Long> {
}
