package io.formulate.common.order.model;

import io.formulate.common.model.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class BaseOrder extends BaseDomain {
    protected String commodityId;
    protected String commodityName;
    protected BigDecimal price;
    protected Integer quantity;
}
