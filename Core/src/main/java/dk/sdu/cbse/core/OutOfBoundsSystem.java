package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.HashSet;
import java.util.List;

public class OutOfBoundsSystem extends System {
    double outsideExtend;

    public OutOfBoundsSystem(World world) {
        this.world = world;
        HashSet<Entity> entities = world.getEntitiesWith(PlayerComponent.class);
        double maxExtend = 0;
        for(Entity e : entities) {
            double entitySize;
            entitySize = e.getComponent(CircleColliderComponent.class).radius;
            if(entitySize > maxExtend) {
                maxExtend = entitySize;
            }
        }

        outsideExtend = maxExtend;
    }

    @Override
    public void update(float dt) {
        HashSet<Entity> entities = world.getEntitiesWith(OutOfBoundsComponent.class, PositionComponent.class);
        for(Entity e : entities) {
            OutOfBoundsComponent outOfBoundsComponent = e.getComponent(OutOfBoundsComponent.class);
            if(outOfBoundsComponent.outOfBoundsAction == OutOfBoundsComponent.OutOfBoundsAction.WRAP) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                if(pos.position.x > world.worldWidth + outsideExtend) {
                    pos.position.x = -outsideExtend;
                }
                if(pos.position.x < -outsideExtend) {
                    pos.position.x = world.worldWidth + outsideExtend;
                }
                if(pos.position.y > world.worldHeight + outsideExtend) {
                    pos.position.y = -outsideExtend;
                }
                if(pos.position.y < -outsideExtend) {
                    pos.position.y = world.worldHeight + outsideExtend;
                }
            }
            else if(outOfBoundsComponent.outOfBoundsAction == OutOfBoundsComponent.OutOfBoundsAction.REMOVE) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                if(pos.position.x > world.worldWidth + outsideExtend) {
                    e.removeThis = true;
                }
                if(pos.position.x < -outsideExtend) {
                    e.removeThis = true;
                }
                if(pos.position.y > world.worldHeight + outsideExtend) {
                    e.removeThis = true;
                }
                if(pos.position.y < -outsideExtend) {
                    e.removeThis = true;
                }
            }
        }
    }
}
