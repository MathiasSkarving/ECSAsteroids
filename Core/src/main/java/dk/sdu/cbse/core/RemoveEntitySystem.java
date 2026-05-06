package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;

import java.util.HashSet;

public class RemoveEntitySystem extends BaseSystem implements IGamePlugin {
    HashSet<Entity> toRemove = new HashSet<>();

    public RemoveEntitySystem() {
    }

    @Override
    public void update(float dt) {
        for(Entity e : world.getEntitiesWith(RenderComponent.class)) {
            if(e.removeThis) {
                toRemove.add(e);
            }
        }

        for(Entity e : toRemove) {
            world.removeEntity(e);
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
