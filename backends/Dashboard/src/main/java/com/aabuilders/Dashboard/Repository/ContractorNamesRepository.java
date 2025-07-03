package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.ContractorNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorNamesRepository extends JpaRepository<ContractorNames , Long> {
}
