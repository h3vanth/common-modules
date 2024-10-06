package io.formulate.common.event;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@Getter
@Setter
@ToString
public class Event {
    protected final String type;

    protected String tenantId;
    protected String propertyId;
    protected DateTime timestamp;

    public Event(String type) {
        this.type = type;
        timestamp = DateTime.now(DateTimeZone.UTC);
    }
}
