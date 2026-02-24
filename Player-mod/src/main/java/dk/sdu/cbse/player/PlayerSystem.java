package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.List;

public class PlayerSystem extends System {
    double maxSpeed = 25;
    double acceleration = 5;
    double dragForce = 0.9;

    @Override
    public void update(float dt) {
        List<Entity> players = world.getEntitiesWith(PlayerComponent.class, RotationComponent.class, VelocityComponent.class, PositionComponent.class, RenderComponent.class);
        Entity player = players.getFirst();

        VelocityComponent velComp = player.getComponent(VelocityComponent.class);
        RotationComponent rotComp = player.getComponent(RotationComponent.class);

        velComp.velX = velComp.directionVel*Math.cos(Math.toRadians(rotComp.angle-90));
        velComp.velY = velComp.directionVel*Math.sin(Math.toRadians(rotComp.angle-90));

        if(world.inputHandler.isKeyPressed(GameKey.LEFT)) {
            rotComp.angle = rotComp.angle-(180*dt);
        }
        if(world.inputHandler.isKeyPressed(GameKey.RIGHT)) {
            rotComp.angle = rotComp.angle+(180*dt);
        }
        if(world.inputHandler.isKeyPressed(GameKey.UP)) {
            velComp.directionVel = Math.min(velComp.directionVel + acceleration * dt, maxSpeed);
        }
        if(!world.inputHandler.isKeyPressed(GameKey.UP)) {
            velComp.directionVel *= (1.0 - dragForce * dt);        }
    }
}
