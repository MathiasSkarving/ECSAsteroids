package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.HashSet;
import java.util.List;

public class PlayerSystem extends System implements Subscriber {
    double maxSpeed = 25;
    double acceleration = 99;
    double dragForce = 5;
    HashSet<GameKey> keysPressed;

    public PlayerSystem() {
        EventBus.getInstance().subscribe(this, KeyEvent.class);
        keysPressed = new HashSet<>();
    }

    public void onEvent(EventType event) {
        if (event.getClass() == KeyEvent.class) {
            if (((KeyEvent) event).getPressed()) {
                keysPressed.add(((KeyEvent) event).getKey());
            } else {
                keysPressed.remove(((KeyEvent) event).getKey());
            }
        }
    }


    @Override
    public void update(float dt) {
        HashSet<Entity> players = world.getEntitiesWith(PlayerComponent.class, RotationComponent.class, VelocityComponent.class, PositionComponent.class, RenderComponent.class);
        for (Entity p : players) {
            VelocityComponent velComp = p.getComponent(VelocityComponent.class);
            RotationComponent rotComp = p.getComponent(RotationComponent.class);

            velComp.velX = velComp.directionVel * Math.cos(Math.toRadians(rotComp.angle + 90));
            velComp.velY = velComp.directionVel * Math.sin(Math.toRadians(rotComp.angle + 90));

            if (keysPressed.contains(GameKey.LEFT)) {
                rotComp.angle = rotComp.angle - (360 * dt);
            }
            if (keysPressed.contains(GameKey.RIGHT)) {
                rotComp.angle = rotComp.angle + (360 * dt);
            }
            if (keysPressed.contains(GameKey.UP)) {
                velComp.directionVel = Math.min(velComp.directionVel + acceleration * dt, maxSpeed);
            }
            if (!keysPressed.contains(GameKey.UP)) {
                velComp.directionVel *= (1.0 - dragForce * dt);
            }
        }
    }
}
