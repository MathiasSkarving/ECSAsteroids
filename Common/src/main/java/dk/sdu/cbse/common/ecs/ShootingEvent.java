package dk.sdu.cbse.common.ecs;

public class ShootingEvent implements EventType {
    public Entity source;
    public ShootingEvent(Entity source) {
        this.source = source;
    }
}
