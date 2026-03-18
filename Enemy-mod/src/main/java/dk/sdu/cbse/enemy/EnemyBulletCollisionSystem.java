package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.commonbullet.BulletComponent;
import dk.sdu.cbse.commonenemy.EnemyComponent;

public class EnemyBulletCollisionSystem extends BaseSystem implements Subscriber {
    public EnemyBulletCollisionSystem() {
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {
        if(event.getClass() == CollisionEvent.class) {
            CollisionEvent collisionEvent = (CollisionEvent) event;

            Entity collisionEntity1 = collisionEvent.entity1;
            Entity collisionEntity2 = collisionEvent.entity2;

            boolean isEntity1ABulletFromPlayer = false;
            if (collisionEntity1.getComponent(BulletComponent.class) != null) {

                if (collisionEntity1.getComponent(BulletComponent.class).owner.getComponent(EnemyComponent.class) == null) {
                    isEntity1ABulletFromPlayer = true;
                }
            }

            boolean isEntity2ABulletFromPlayer = false;
            if (collisionEntity2.getComponent(BulletComponent.class) != null) {

                if (collisionEntity2.getComponent(BulletComponent.class).owner.getComponent(EnemyComponent.class) == null) {
                    isEntity2ABulletFromPlayer = true;
                }
            }

            // If one of the bullets is owned by an enemy
            if (isEntity1ABulletFromPlayer || isEntity2ABulletFromPlayer) {

                if (isEntity1ABulletFromPlayer) {
                    if (collisionEntity2.getComponent(EnemyComponent.class) != null) {
                        collisionEntity1.removeThis = true;
                        collisionEntity2.removeThis = true;
                    }
                }
                if (isEntity2ABulletFromPlayer) {
                    if (collisionEntity1.getComponent(EnemyComponent.class) != null) {
                        collisionEntity1.removeThis = true;
                        collisionEntity2.removeThis = true;
                    }
                }
            }


        }
    }
}
