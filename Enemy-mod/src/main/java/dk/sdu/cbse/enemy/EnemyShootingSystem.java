package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.commonenemy.EnemyComponent;

import java.util.HashSet;
import java.util.Vector;

public class EnemyShootingSystem extends BaseSystem {
    public double shootInterval = 4000;

    // Distance needed for the enemy to spot and shoot at the player
    public double shootDistance = 350;
    @Override
    public void update(float dt) {
        // Goes through all enemies and shoots if close enough to player
        for (Entity enemy : world.getEntitiesWith(EnemyComponent.class)) {
            Entity closestPlayer = null;
            double cloestDistance = Double.MAX_VALUE;
            // Finds closest player
            for(Entity player : world.getEntitiesWith(PlayerComponent.class)) {
                double distance = Helpers.calculateDistance(player.getComponent(PositionComponent.class), enemy.getComponent(PositionComponent.class));
                if(distance < cloestDistance) {
                    cloestDistance = distance;
                    closestPlayer = player;
                }
            }
            if(closestPlayer == null) return;

            // Checks if closest player is close enough
            if(Helpers.calculateDistance(enemy.getComponent(PositionComponent.class), closestPlayer.getComponent(PositionComponent.class)) < shootDistance) {
                double now = System.nanoTime() / (double) 1000000;
                EnemyEntity enemyEntity = (EnemyEntity)enemy;
                if(now - enemyEntity.lastShot > shootInterval) {
                    RotationComponent rotationComponent = enemy.getComponent(RotationComponent.class);
                    Vector2 playerPosition = closestPlayer.getComponent(PositionComponent.class).position;
                    Vector2 enemyPosition = enemy.getComponent(PositionComponent.class).position;

                    Vector2 direction = playerPosition.subtract(enemyPosition);
                    rotationComponent.angle = Math.toDegrees(Math.atan2(direction.y, direction.x));

                    EventBus.getInstance().notifySubscribers(new ShootingEvent(enemy));
                    enemyEntity.lastShot = now;
                }
            }
        }
    }
}
