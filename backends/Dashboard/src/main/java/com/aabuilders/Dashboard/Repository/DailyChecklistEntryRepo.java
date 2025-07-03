package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.DailyChecklistEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DailyChecklistEntryRepo extends JpaRepository<DailyChecklistEntry, Long> {
    List<DailyChecklistEntry> findByChecklistNumber(int checklistNumber);
    @Query("SELECT MAX(e.checklistNumber) FROM DailyChecklistEntry e")
    Integer findMaxChecklistNumber();

}
