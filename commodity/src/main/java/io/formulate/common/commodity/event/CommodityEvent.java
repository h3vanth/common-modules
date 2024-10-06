package io.formulate.common.commodity.event;

import io.formulate.common.event.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class CommodityEvent extends Event {
    protected boolean available;
    protected Integer availableQuantity;
    protected List<String> categories;
    protected String commodityId;
    protected String commodityName;
    protected String description;
    protected String imageUrl;
    protected BigDecimal price;

    public CommodityEvent(String type) {
        super(type);
    }
}
