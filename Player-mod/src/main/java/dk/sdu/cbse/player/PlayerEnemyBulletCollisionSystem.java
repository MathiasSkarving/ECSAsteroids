package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;
import dk.sdu.cbse.commonbullet.BulletComponent;

public class PlayerEnemyBulletCollisionSystem extends BaseSystem implements Subscriber {

    public PlayerEnemyBulletCollisionSystem() {
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {
        Entity collisionEntity1 = ((CollisionEvent) event).entity1;
        Entity collisionEntity2 = ((CollisionEvent) event).entity2;

        boolean isEntity1ABulletFromEnemy = false;
        if (collisionEntity1.getComponent(BulletComponent.class) != null) {
            System.out.println("a bullet");

            if (collisionEntity1.getComponent(BulletComponent.class).owner.getComponent(PlayerComponent.class) == null) {
                isEntity1ABulletFromEnemy = true;
            }
        }

        boolean isEntity2ABulletFromEnemy = false;
        if (collisionEntity2.getComponent(BulletComponent.class) != null) {
            System.out.println("a bullet");

            if (collisionEntity2.getComponent(BulletComponent.class).owner.getComponent(PlayerComponent.class) == null) {
                isEntity2ABulletFromEnemy = true;
            }
        }

        // If one of the bullets is owned by an enemy
        if (isEntity1ABulletFromEnemy || isEntity2ABulletFromEnemy) {

            if (isEntity1ABulletFromEnemy) {
                if (collisionEntity2.getComponent(PlayerComponent.class) != null) {
                    collisionEntity1.removeThis = true;
                    collisionEntity2.removeThis = true;
                }
            }
            if (isEntity2ABulletFromEnemy) {
                if (collisionEntity1.getComponent(PlayerComponent.class) != null) {
                    collisionEntity1.removeThis = true;
                    collisionEntity2.removeThis = true;
                }
            }
        }
    }
}
