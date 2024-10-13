package io.formulate.common.order.model;

import io.formulate.common.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class BaseOrder extends BaseDomain {
    public static final String COMMODITY_ID = "commodityId";
    public static final String COMMODITY_NAME = "commodityName";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";

    protected String commodityId;
    protected String commodityName;
    protected BigDecimal price;
    protected Integer quantity;
}
