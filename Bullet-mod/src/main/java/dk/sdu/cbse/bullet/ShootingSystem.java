package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.HashSet;

public class ShootingSystem extends System implements Subscriber {
    HashSet<GameKey> keysPressed = new HashSet<>();

    public ShootingSystem() {
        java.lang.System.out.println("ShootingSystem instantiated, EventBus: " + EventBus.getInstance().hashCode());
        EventBus.getInstance().subscribe(this, KeyEvent.class);
    }

    @Override
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
        HashSet<Entity> entities = world.getEntitiesWith(PlayerComponent.class);
        for (Entity e : entities) {
            PlayerComponent playerComponent = e.getComponent(PlayerComponent.class);
            if (keysPressed.contains(playerComponent.gameActionGameKeyHashMap.get(GameAction.Shoot))) {
                java.lang.System.out.println("SHOT");
                PositionComponent positionComponent = e.getComponent(PositionComponent.class);
                PositionComponent bulletPosition = new PositionComponent(new Vector2());
                bulletPosition.position = positionComponent.position;
                RotationComponent rotationComponent = e.getComponent(RotationComponent.class);
                Vector2 bulletVelocity = new Vector2(rotationComponent.angle + rotationComponent.angleOffset);
                bulletVelocity = bulletVelocity.scale(500);
                world.addEntity(new BulletEntity(5, bulletVelocity, positionComponent.position));
            }
        }
    }
}
