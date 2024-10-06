package io.formulate.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
public abstract class BaseDomain {
    public static final String ID = "_id";
    public static final String PROPERTY_ID = "propertyId";
    public static final String TENANT_ID = "tenantId";

    @Id
    protected String id;
    protected String tenantId;
    protected String propertyId;
}
