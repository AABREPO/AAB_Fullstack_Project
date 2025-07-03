package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Model.PaintCalculation;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaintCalculationRepository extends JpaRepository<PaintCalculation, Long> {
}
