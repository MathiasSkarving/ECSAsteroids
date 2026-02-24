package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;

public class PlayerEntity extends Entity {
    public PlayerEntity() {
        addComponent(new VelocityComponent());
        addComponent(new PositionComponent());
        addComponent(new RenderComponent());
        addComponent(new RotationComponent());
        addComponent(new PlayerComponent());
    }
}
