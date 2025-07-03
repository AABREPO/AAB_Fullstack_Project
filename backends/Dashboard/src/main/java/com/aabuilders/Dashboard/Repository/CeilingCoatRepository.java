package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.CeilingCoat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CeilingCoatRepository extends JpaRepository<CeilingCoat, Long> {
    List<CeilingCoat> findByClientNameAndFileNameAndDate(String clientName, String fileName, String date);
}

