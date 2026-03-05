package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.ecs.*;

public class AsteroidCollisionSystem extends BaseSystem implements Subscriber {
    public AsteroidCollisionSystem() {
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {
        CollisionEvent collisionEvent = (CollisionEvent) event;

        // If they are both asteroids
        if (collisionEvent.entity1.getComponent(AsteroidComponent.class) != null && collisionEvent.entity2.getComponent(AsteroidComponent.class) != null) {
            VelocityComponent v1 = collisionEvent.entity1.getComponent(VelocityComponent.class);
            VelocityComponent v2 = collisionEvent.entity2.getComponent(VelocityComponent.class);
            PositionComponent p1 = collisionEvent.entity1.getComponent(PositionComponent.class);
            PositionComponent p2 = collisionEvent.entity2.getComponent(PositionComponent.class);
            CircleColliderComponent c1 = collisionEvent.entity1.getComponent(CircleColliderComponent.class);
            CircleColliderComponent c2 = collisionEvent.entity2.getComponent(CircleColliderComponent.class);

            double overlap = (p1.position.subtract(p2.position)).magnitude() - (c1.radius + c2.radius);
            Vector2 collisionNormal = p1.position.subtract(p2.position).normalize();


            if (overlap < 0) {
                p1.position = p1.position.subtract(collisionNormal.scale(overlap / 2));
                p2.position = p2.position.add(collisionNormal.scale(overlap / 2));
            }

            System.out.println("Changing VELOCITIES");
            Vector2 OldVel = v1.velocity;
            v1.velocity = v2.velocity;
            v2.velocity = OldVel;
        }

        if (collisionEvent.entity1.getComponent(AsteroidComponent.class) != null || collisionEvent.entity2.getComponent(AsteroidComponent.class) != null) {
            if (collisionEvent.entity1.getComponent(AsteroidComponent.class) != null && collisionEvent.entity2.getComponent(AsteroidComponent.class) != null) {
                return;
            } else {
                collisionEvent.entity1.removeThis = true;
                collisionEvent.entity2.removeThis = true;
            }
        }
    }

    @Override
    public void update(float dt) {

    }
}
