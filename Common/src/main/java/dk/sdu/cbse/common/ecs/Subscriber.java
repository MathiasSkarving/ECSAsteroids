package dk.sdu.cbse.common.ecs;

public interface Subscriber<T extends EventType> {
    public void onEvent(T event);
}
