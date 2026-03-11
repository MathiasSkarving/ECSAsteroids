package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;

import java.util.HashSet;

public class MovingSystem extends BaseSystem {
    public MovingSystem() {
    }

    @Override
    public void update(float dt) {
        HashSet<Entity> entities = world.getEntitiesWith(VelocityComponent.class, PositionComponent.class);
        for (Entity e : entities) {
           PositionComponent posComp = e.getComponent(PositionComponent.class);
           VelocityComponent velComp = e.getComponent(VelocityComponent.class);

           posComp.position = posComp.position.add(velComp.velocity.scale(dt));
        }
    }
}
