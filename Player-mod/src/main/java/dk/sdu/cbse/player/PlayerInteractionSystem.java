package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.HashSet;

public class PlayerInteractionSystem extends System implements Subscriber {
    double thrustForce = 2500;
    double dragForce = 0.5;
    HashSet<GameKey> keysPressed;

    public PlayerInteractionSystem() {
        EventBus.getInstance().subscribe(this, KeyEvent.class);
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
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
            VelocityComponent velocityComponent = p.getComponent(VelocityComponent.class);
            RotationComponent rotationComponent = p.getComponent(RotationComponent.class);
            AccelerationComponent accelerationComponent = p.getComponent(AccelerationComponent.class);
            PlayerComponent playerComponent = p.getComponent(PlayerComponent.class);

            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.RotateLeft))) {
                rotationComponent.angle = rotationComponent.angle - (270 * dt);
            }
            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.RotateRight))) {
                rotationComponent.angle = rotationComponent.angle + (270 * dt);
            }
            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.Accelerate))) {
                double angle = rotationComponent.angle + rotationComponent.angleOffset;
                accelerationComponent.acceleration = new Vector2(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle))).scale(thrustForce);
            }
            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.Shoot))) {
                EventBus.getInstance().notifySubscribers(new ShootingEvent(p));
            }
            if (!keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.Accelerate))) {
                accelerationComponent.acceleration = new Vector2(0,0);
            }

            velocityComponent.velocity = velocityComponent.velocity.scale(Math.pow(dragForce, dt));
            velocityComponent.velocity = velocityComponent.velocity.add(accelerationComponent.acceleration.scale(dt));
        }
    }
}
