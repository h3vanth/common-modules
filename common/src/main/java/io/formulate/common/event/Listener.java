package io.formulate.common.event;

import com.google.common.eventbus.Subscribe;

public interface Listener {
    @Subscribe
    void handleEvent(Event event);
}
