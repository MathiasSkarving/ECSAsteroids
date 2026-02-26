package dk.sdu.cbse.common.ecs;

public class CollisionEvent implements EventType {
    public Entity entity1;
    public Entity entity2;

    public CollisionEvent(Entity entity1, Entity entity2) {
        this.entity1 = entity1;
        this.entity2 = entity2;
    }
}
