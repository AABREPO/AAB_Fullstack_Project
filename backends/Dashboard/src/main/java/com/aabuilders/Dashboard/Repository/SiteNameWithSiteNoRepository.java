package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Entity.SiteNamesWithSiteNo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteNameWithSiteNoRepository extends JpaRepository<SiteNamesWithSiteNo,Long> {
}
