package dk.sdu.cbse.common.ecs;

public class ShootingEvent extends EventType {
    public Entity source;
    public ShootingEvent(Entity source) {
        this.source = source;
    }
}
