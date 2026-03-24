package com.project.dealer_system.audit.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dealerId;
    private Long actorId;
    private String module;
    private String action;

    @Column(columnDefinition = "TEXT")
    private String metadata;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getDealerId() { return dealerId; }
    public Long getActorId() { return actorId; }
    public String getModule() { return module; }
    public String getAction() { return action; }
    public String getMetadata() { return metadata; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setDealerId(Long dealerId) { this.dealerId = dealerId; }
    public void setActorId(Long actorId) { this.actorId = actorId; }
    public void setModule(String module) { this.module = module; }
    public void setAction(String action) { this.action = action; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}