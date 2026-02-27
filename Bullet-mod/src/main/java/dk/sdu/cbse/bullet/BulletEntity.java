package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.ecs.*;

public class BulletEntity extends Entity {
    public BulletEntity() {
        addComponent(new VelocityComponent(new Vector2(0,0)));
        addComponent(new PositionComponent(new Vector2(0,0)));
        addComponent(new RenderComponent());
        addComponent(new CircleColliderComponent());
    }
}
