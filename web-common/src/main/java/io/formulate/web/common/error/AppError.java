package io.formulate.web.common.error;

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
        timestamp = DateTime.now(DateTimeZone.UTC);
        this.messages = List.of(message);
    }

    public AppError(List<String> messages) {
        timestamp = DateTime.now(DateTimeZone.UTC);
        this.messages = messages;
    }
}
