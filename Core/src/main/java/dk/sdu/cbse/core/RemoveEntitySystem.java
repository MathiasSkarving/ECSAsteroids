package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.Entity;
import dk.sdu.cbse.common.ecs.RenderComponent;
import dk.sdu.cbse.common.ecs.System;

public class RemoveEntitySystem extends System {
    @Override
    public void update(float dt) {
        for(Entity e : world.getEntitiesWith()) {
            if(e.removeThis) {
                world.removeEntity(e);
            }
        }
    }
}
