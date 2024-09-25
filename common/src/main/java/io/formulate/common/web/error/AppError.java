package io.formulate.common.web.error;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.List;

@Getter
@Setter
public class AppError {
    private List<String> messages;
    private DateTime timestamp;

    public AppError(String message) {
        this(List.of(message));
    }

    public AppError(List<String> messages) {
        this.messages = messages;
        timestamp = DateTime.now(DateTimeZone.UTC);
    }
}
