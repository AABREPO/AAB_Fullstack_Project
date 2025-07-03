package com.example.Dashboard2.Repository;

import com.example.Dashboard2.Entity.RentalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalFormRepository extends JpaRepository<RentalForm, Long> {

    @Query(value = "SELECT * FROM rental_form WHERE paid_on_date >= DATE_FORMAT(CURDATE(), '%Y-%m-01') AND paid_on_date < DATE_ADD(DATE_FORMAT(CURDATE(), '%Y-%m-01'), INTERVAL 1 MONTH)", nativeQuery = true)
    List<RentalForm> findCurrentMonthEntries();

    @Query(value = "SELECT * FROM rental_form WHERE paid_on_date >= :startDate AND paid_on_date < :endDate", nativeQuery = true)
    List<RentalForm> findEntriesBetweenDates(java.sql.Date startDate, java.sql.Date endDate);

}

