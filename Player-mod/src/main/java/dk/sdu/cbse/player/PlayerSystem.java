package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.List;

//TODO Implement eventbus in player system

public class PlayerSystem extends System implements Subscriber {
    double maxSpeed = 25;
    double acceleration = 99;
    double dragForce = 5;

    public PlayerSystem() {
        EventBus.getInstance().subscribe(this, KeyEvent.class);
    }

    public void onEvent(Class<? extends EventType> event) {
    }


    @Override
    public void update(float dt) {
        List<Entity> players = world.getEntitiesWith(PlayerComponent.class, RotationComponent.class, VelocityComponent.class, PositionComponent.class, RenderComponent.class);
        Entity player = players.getFirst();

        VelocityComponent velComp = player.getComponent(VelocityComponent.class);
        RotationComponent rotComp = player.getComponent(RotationComponent.class);

        velComp.velX = velComp.directionVel*Math.cos(Math.toRadians(rotComp.angle-90));
        velComp.velY = velComp.directionVel*Math.sin(Math.toRadians(rotComp.angle-90));

        if(world.inputHandler.isKeyPressed(GameKey.LEFT)) {
            rotComp.angle = rotComp.angle-(360*dt);
        }
        if(world.inputHandler.isKeyPressed(GameKey.RIGHT)) {
            rotComp.angle = rotComp.angle+(360*dt);
        }
        if(world.inputHandler.isKeyPressed(GameKey.UP)) {
            velComp.directionVel = Math.min(velComp.directionVel + acceleration * dt, maxSpeed);
        }
        if(!world.inputHandler.isKeyPressed(GameKey.UP)) {
            velComp.directionVel *= (1.0 - dragForce * dt);        }
    }
}
