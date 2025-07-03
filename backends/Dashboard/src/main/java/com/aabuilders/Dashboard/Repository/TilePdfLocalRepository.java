package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Model.TilePdfLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TilePdfLocalRepository extends JpaRepository<TilePdfLocal, Long> {
    Optional<TilePdfLocal> findByFileLabelAndFileTypeAndClientId(String fileLabel, String fileType, String clientId);
}
