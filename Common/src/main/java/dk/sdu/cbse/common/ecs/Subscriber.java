package dk.sdu.cbse.common.ecs;

import javafx.event.Event;

public interface Subscriber<T extends EventType> {
    public void onEvent(T event);
}
