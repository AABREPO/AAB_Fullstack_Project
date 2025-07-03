package com.example.Dashboard2.Repository;

import com.example.Dashboard2.Entity.POTypeColorList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface POTypeColorListRepository extends JpaRepository<POTypeColorList, Long> {
}
