package com.project.dealer_system.audit.repository;

import com.project.dealer_system.audit.entity.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    @Query("SELECT a FROM AuditLog a WHERE a.dealerId = :dealerId " +
            "AND (:module IS NULL OR a.module = :module) " +
            "AND (:action IS NULL OR a.action = :action) " +
            "AND (:from IS NULL OR a.createdAt >= :from) " +
            "AND (:to IS NULL OR a.createdAt <= :to)")
    Page<AuditLog> findWithFilters(
            @Param("dealerId") Long dealerId,
            @Param("module") String module,
            @Param("action") String action,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            Pageable pageable
    );
}