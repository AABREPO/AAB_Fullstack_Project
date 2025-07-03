package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.MachineTools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineToolsRepository extends JpaRepository<MachineTools, Long> {
}
