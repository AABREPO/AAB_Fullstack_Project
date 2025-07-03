package com.aabuilders.Dashboard.Repository;

import com.aabuilders.Dashboard.Model.PaintPdfLocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PaintPdfLocalRepository extends JpaRepository<PaintPdfLocal, Long> {
    Optional<PaintPdfLocal> findByFileLabelAndFileTypeAndClientId(String fileLabel, String fileType, String clientId);
}
