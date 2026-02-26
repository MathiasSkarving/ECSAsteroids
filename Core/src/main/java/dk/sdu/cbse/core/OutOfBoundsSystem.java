package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.HashSet;
import java.util.List;

public class OutOfBoundsSystem extends System {
    public OutOfBoundsSystem(World world) {
        this.world = world;
    }

    @Override
    public void update(float dt) {
        HashSet<Entity> entities = world.getEntitiesWith(OutOfBoundsComponent.class, PositionComponent.class);
        for(Entity e : entities) {
            OutOfBoundsComponent outOfBoundsComponent = e.getComponent(OutOfBoundsComponent.class);
            if(outOfBoundsComponent.outOfBoundsAction == OutOfBoundsComponent.OutOfBoundsAction.WRAP) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                if(pos.x > world.worldWidth + 100) {
                    pos.x = -100;
                }
                if(pos.x < -100) {
                    pos.x = world.worldWidth + 100;
                }
                if(pos.y > world.worldHeight + 100) {
                    pos.y = -100;
                }
                if(pos.y < -100) {
                    pos.y = world.worldHeight + 100;
                }
            }
            else if(outOfBoundsComponent.outOfBoundsAction == OutOfBoundsComponent.OutOfBoundsAction.REMOVE) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                if(pos.x > world.worldWidth + 100) {
                    e.removeThis = true;
                }
                if(pos.x < -100) {
                    e.removeThis = true;
                }
                if(pos.y > world.worldHeight + 100) {
                    e.removeThis = true;
                }
                if(pos.y < -100) {
                    e.removeThis = true;
                }
            }
        }
    }
}
