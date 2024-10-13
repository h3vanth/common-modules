package io.formulate.common.domain;

import io.formulate.common.model.PropertyAware;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public abstract class BaseDomain implements PropertyAware {
    public static final String ID = "_id";
    public static final String PROPERTY_ID = "propertyId";
    public static final String TENANT_ID = "tenantId";

    @Id
    protected String id;
    protected String tenantId;
    protected String propertyId;
}
