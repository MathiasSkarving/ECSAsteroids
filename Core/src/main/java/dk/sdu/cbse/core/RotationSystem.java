package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;

import java.util.HashSet;

public class RotationSystem extends BaseSystem implements IGamePlugin {
    public RotationSystem() {
    }

    @Override
    public void update(float dt) {
        HashSet<Entity> entities = world.getEntitiesWith(RotationComponent.class, RotationalVelocityComponent.class);
        for (Entity e : entities) {
           RotationComponent rotationComponent = e.getComponent(RotationComponent.class);
           RotationalVelocityComponent rotationalVelocityComponent = e.getComponent(RotationalVelocityComponent.class);
           rotationComponent.angle += rotationalVelocityComponent.rotationalVelocity * dt;
        }
    }

    @Override
    public void start(World world) {
        world.addSystem(this);
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 0;
    }
}
