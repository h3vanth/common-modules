package io.formulate.common.order.event;

import io.formulate.common.event.Event;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderEvent extends Event {
    protected String commodityId;
    protected String orderId;
    protected BigDecimal price;
    protected String commodityName;
    protected Integer quantity;

    public OrderEvent(String type) {
        super(type);
    }
}
