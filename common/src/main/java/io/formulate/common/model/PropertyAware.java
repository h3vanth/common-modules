package io.formulate.common.model;

public interface PropertyAware {
    void setTenantId(String tenantId);
    String getTenantId();
    void setPropertyId(String propertyId);
    String getPropertyId();
}
