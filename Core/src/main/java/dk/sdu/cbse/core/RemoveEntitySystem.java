package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.Entity;
import dk.sdu.cbse.common.ecs.RenderComponent;
import dk.sdu.cbse.common.ecs.BaseSystem;
import dk.sdu.cbse.common.ecs.World;

import java.util.HashSet;

public class RemoveEntitySystem extends BaseSystem {
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
}
