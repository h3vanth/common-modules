package io.formulate.common.commodity.model;

import io.formulate.common.domain.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class BaseCommodity extends BaseDomain {
    public static final String AVAILABLE = "available";
    public static final String AVAILABLE_QUANTITY = "availableQuantity";
    public static final String CATEGORIES = "categories";
    public static final String COMMODITY_NAME = "commodityName";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";

    protected Boolean available;
    protected Integer availableQuantity;
    protected Set<String> categories;
    protected String commodityName;
    protected String description;
    protected String imageUrl;
    protected BigDecimal price;
}
