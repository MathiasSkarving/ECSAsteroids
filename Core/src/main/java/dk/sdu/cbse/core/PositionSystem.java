package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class PositionSystem extends System {
    public PositionSystem(World world) {
        this.world = world;
    }

    @Override
    public void update(float dt) {
        List<Entity> entities = world.getEntitiesWith(VelocityComponent.class, PositionComponent.class);
        for (Entity e : entities) {
           PositionComponent posComp = e.getComponent(PositionComponent.class);
           VelocityComponent velComp = e.getComponent(VelocityComponent.class);

           posComp.x += velComp.velX;
           posComp.y += velComp.velY;
        }
    }
}
