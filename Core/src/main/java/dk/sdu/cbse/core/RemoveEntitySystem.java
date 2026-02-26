package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.Entity;
import dk.sdu.cbse.common.ecs.RenderComponent;
import dk.sdu.cbse.common.ecs.System;
import dk.sdu.cbse.common.ecs.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RemoveEntitySystem extends System {
    HashSet<Entity> toRemove = new HashSet<>();

    public RemoveEntitySystem(World world) {
        this.world = world;
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
