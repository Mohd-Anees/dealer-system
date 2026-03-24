package com.project.dealer_system.audit.service;

import com.project.dealer_system.audit.dto.AuditLogRequest;
import com.project.dealer_system.audit.entity.AuditLog;
import com.project.dealer_system.audit.repository.AuditLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public void log(AuditLogRequest request) {
        AuditLog log = new AuditLog();
        log.setDealerId(request.getDealerId());
        log.setActorId(request.getActorId());
        log.setModule(request.getModule());
        log.setAction(request.getAction());
        log.setMetadata(request.getMetadata());
        log.setCreatedAt(LocalDateTime.now());
        repository.save(log);
    }

    public Page<AuditLog> getLogs(Long dealerId, String module, String action,
                                  LocalDateTime from, LocalDateTime to, Pageable pageable) {
        return repository.findWithFilters(dealerId, module, action, from, to, pageable);
    }
}