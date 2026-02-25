package dk.sdu.cbse.common.ecs;

@FunctionalInterface
public interface Subscriber {
    public void onEvent(EventType event);
}
