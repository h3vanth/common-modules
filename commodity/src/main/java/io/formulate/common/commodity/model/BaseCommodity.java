package io.formulate.common.commodity.model;

import io.formulate.common.model.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class BaseCommodity extends BaseDomain {
    protected boolean available;
    protected Integer availableQuantity;
    protected Set<String> categories;
    protected String commodityName;
    protected String description;
    protected BigDecimal price;
}
