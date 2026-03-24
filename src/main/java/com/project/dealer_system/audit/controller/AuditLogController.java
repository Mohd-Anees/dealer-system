package com.project.dealer_system.audit.controller;

import com.project.dealer_system.audit.entity.AuditLog;
import com.project.dealer_system.audit.service.AuditLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping("/dealer/{dealerId}")
    public Page<AuditLog> getLogs(
            @PathVariable Long dealerId,
            @RequestHeader("X-Dealer-Id") Long requestingDealerId,
            @RequestParam(required = false) String module,
            @RequestParam(required = false) String action,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            Pageable pageable) {

        if (!dealerId.equals(requestingDealerId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied: You can only view your own logs");
        }

        return auditLogService.getLogs(dealerId, module, action, from, to, pageable);
    }
}