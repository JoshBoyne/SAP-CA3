package com.sapca3.forum.repository;

import com.sapca3.forum.model.Report;
import com.sapca3.forum.model.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>{
    List<Report> findByStatusOrderByCreatedAtDesc(ReportStatus status);
    boolean existsByPost_IdAndReporter_Id(Long postId, Long reportId);
}