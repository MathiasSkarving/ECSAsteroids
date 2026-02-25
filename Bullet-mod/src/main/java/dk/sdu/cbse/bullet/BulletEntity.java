package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.ecs.Entity;
import dk.sdu.cbse.common.ecs.PositionComponent;
import dk.sdu.cbse.common.ecs.RenderComponent;
import dk.sdu.cbse.common.ecs.VelocityComponent;

public class BulletEntity extends Entity {
    public BulletEntity() {
        addComponent(new VelocityComponent());
        addComponent(new PositionComponent());
        addComponent(new RenderComponent());
    }
}
