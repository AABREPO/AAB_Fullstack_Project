package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Model.BathFixtureCalculation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BathFixtureCalculationRepository extends JpaRepository<BathFixtureCalculation,Long> {
    Optional<BathFixtureCalculation> findByClientNameAndFileName(String clientName, String fileName);
}
