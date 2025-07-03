package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.PaintDeductionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PaintDeductionTableRepository extends JpaRepository<PaintDeductionTable,Long> {
    List<PaintDeductionTable> findByClientNameAndFileNameAndDate(String clientName, String fileName, String date);
}
