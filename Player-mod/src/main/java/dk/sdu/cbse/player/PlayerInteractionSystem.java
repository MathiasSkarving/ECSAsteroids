package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;

import java.util.HashSet;

public class PlayerInteractionSystem extends BaseSystem implements Subscriber {
    double shootInterval = 750;
    double thrustForce = 2500;
    double dragForce = 0.2;
    HashSet<GameKey> keysPressed;

    public PlayerInteractionSystem() {
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
            VelocityComponent velocityComponent = p.getComponent(VelocityComponent.class);
            RotationComponent rotationComponent = p.getComponent(RotationComponent.class);
            AccelerationComponent accelerationComponent = p.getComponent(AccelerationComponent.class);
            PlayerComponent playerComponent = p.getComponent(PlayerComponent.class);
            RotationalVelocityComponent rotationalVelocityComponent = p.getComponent(RotationalVelocityComponent.class);
            RotationalAccelerationComponent rotationalAccelerationComponent = p.getComponent(RotationalAccelerationComponent.class);

            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.RotateLeft))) {
                rotationalAccelerationComponent.rotationalAcceleration = -thrustForce * 1;
            }
            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.RotateRight))) {
                rotationalAccelerationComponent.rotationalAcceleration = thrustForce * 1;
            }
            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.Accelerate))) {
                double angle = rotationComponent.angle + rotationComponent.angleOffset;
                accelerationComponent.acceleration = new Vector2(Math.cos(Math.toRadians(angle)), Math.sin(Math.toRadians(angle))).scale(thrustForce);
            }
            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.Shoot))) {
                double now = System.nanoTime() / (double) 1000000;
                PlayerEntity player = (PlayerEntity)p;
                if(now - player.lastShot > shootInterval) {
                    EventBus.getInstance().notifySubscribers(new ShootingEvent(p));
                    player.lastShot = now;
                }
            }
            if (!keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.Accelerate))) {
                accelerationComponent.acceleration = new Vector2(0,0);
            }
            if (!keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.RotateLeft)) && !keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.RotateRight))) {
                rotationalAccelerationComponent.rotationalAcceleration = 0;
            }

            velocityComponent.velocity = velocityComponent.velocity.scale(Math.pow(dragForce, dt));
            velocityComponent.velocity = velocityComponent.velocity.add(accelerationComponent.acceleration.scale(dt));

            rotationalVelocityComponent.rotationalVelocity = rotationalVelocityComponent.rotationalVelocity * (Math.pow(0.01, dt)); // The rocket automatically tries to counter steer a little bit to stabilize velocity
            rotationalVelocityComponent.rotationalVelocity += rotationalAccelerationComponent.rotationalAcceleration * dt;
        }
    }
}
