package io.formulate.common.event;

import com.google.common.eventbus.EventBus;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EventHandler {
    private final EventBus eventBus;

    public void emitEvent(Event event) {
        eventBus.post(event);
    }

    public void emitEvents(List<Event> events) {
        events.forEach(this::emitEvent);
    }
}
