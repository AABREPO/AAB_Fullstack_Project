package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Model.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
    // Find calculations by client name starting with the given prefix
    List<Calculation> findByClientNameStartingWith(String clientName);
    long countByClientNameStartingWith(String clientName);
}
