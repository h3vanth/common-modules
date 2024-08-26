package io.formulate.common.event;

import com.google.common.eventbus.EventBus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class EventHandler {
    private final EventBus eventBus;

    public void emitEvent(Event event) {
        log.info("[{}] Emitting event {}", Thread.currentThread().getName(), event);
        eventBus.post(event);
    }

    public void emitEvents(List<Event> events) {
        events.forEach(event -> {
            emitEvent(event);
        });
    }
}
