package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.CalculationFormulas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationFormulasRepository extends JpaRepository<CalculationFormulas,Long> {
}
