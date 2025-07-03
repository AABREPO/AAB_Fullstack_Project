package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.RccCalculationFormulas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RccCalculationFormulasRepository extends JpaRepository<RccCalculationFormulas,Long> {
}
