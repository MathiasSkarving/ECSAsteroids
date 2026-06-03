package dk.sdu.cbse.common.ecs;

/**
 * Interface for event subscribers
 * @param <T> Event type
 */
public interface Subscriber<T extends EventType> {

    /**
     * Called when an event is published
     * @param event the Event Type to be handled
     */
    void onEvent(T event);
}
