package com.project.dealer_system.audit.dto;

public class AuditLogRequest {

    private Long dealerId;
    private Long actorId;
    private String module;
    private String action;
    private String metadata;

    public AuditLogRequest() {}

    public AuditLogRequest(Long dealerId, Long actorId, String module, String action, String metadata) {
        this.dealerId = dealerId;
        this.actorId = actorId;
        this.module = module;
        this.action = action;
        this.metadata = metadata;
    }

    public Long getDealerId() { return dealerId; }
    public Long getActorId() { return actorId; }
    public String getModule() { return module; }
    public String getAction() { return action; }
    public String getMetadata() { return metadata; }

    public void setDealerId(Long dealerId) { this.dealerId = dealerId; }
    public void setActorId(Long actorId) { this.actorId = actorId; }
    public void setModule(String module) { this.module = module; }
    public void setAction(String action) { this.action = action; }
    public void setMetadata(String metadata) { this.metadata = metadata; }
}