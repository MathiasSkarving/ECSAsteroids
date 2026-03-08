package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.ecs.*;

public class AsteroidCollisionSystem extends BaseSystem implements Subscriber {
    public double decreasePerSplit = 0.65;

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
            RotationalVelocityComponent rv1 = collisionEvent.entity1.getComponent(RotationalVelocityComponent.class);
            RotationalVelocityComponent rv2 = collisionEvent.entity2.getComponent(RotationalVelocityComponent.class);

            CircleColliderComponent c1 = collisionEvent.entity1.getComponent(CircleColliderComponent.class);
            CircleColliderComponent c2 = collisionEvent.entity2.getComponent(CircleColliderComponent.class);

            double overlap = (p1.position.subtract(p2.position)).magnitude() - (c1.radius + c2.radius);
            Vector2 collisionNormal = p1.position.subtract(p2.position).normalize();


            if (overlap < 0) {
                p1.position = p1.position.subtract(collisionNormal.scale(overlap / 2));
                p2.position = p2.position.add(collisionNormal.scale(overlap / 2));
            }

            Vector2 OldVel = v1.velocity;
            v1.velocity = v2.velocity;
            v2.velocity = OldVel;

            double combined = rv1.rotationalVelocity + rv2.rotationalVelocity;
            rv1.rotationalVelocity = combined/2;
            rv2.rotationalVelocity = combined/2;
        }

        // If one of them is an asteroid
        if (collisionEvent.entity1.getComponent(AsteroidComponent.class) != null || collisionEvent.entity2.getComponent(AsteroidComponent.class) != null) {

            if (collisionEvent.entity1.getComponent(AsteroidComponent.class) != null && collisionEvent.entity2.getComponent(AsteroidComponent.class) != null) {
                return;
            }
            // XOR operation
            else {
                if (collisionEvent.entity1.getComponent(AsteroidComponent.class) != null) {
                    AsteroidComponent asteroidComponentParent = collisionEvent.entity1.getComponent(AsteroidComponent.class);
                    PositionComponent positionComponentParent = collisionEvent.entity1.getComponent(PositionComponent.class);
                    if (asteroidComponentParent.splitsLeft < 1) {
                        collisionEvent.entity1.removeThis = true;
                        collisionEvent.entity2.removeThis = true;
                        NotifyDestruction(new AsteroidDestructionEvent(collisionEvent.entity2));

                    } else {
                        int newSplitsLeft = asteroidComponentParent.splitsLeft - 1;
                        int newSplitsInto = asteroidComponentParent.splitsInto;

                        for (int i = 0; i < newSplitsInto; i++) {
                            AsteroidEntity entity = new AsteroidEntity(asteroidComponentParent.scale * decreasePerSplit, positionComponentParent.position.x, positionComponentParent.position.y, Helpers.getRandomVector(), newSplitsLeft, newSplitsInto);
                            world.addEntity(entity);
                        }

                        collisionEvent.entity1.removeThis = true;
                        collisionEvent.entity2.removeThis = true;
                        NotifyDestruction(new AsteroidDestructionEvent(collisionEvent.entity2));

                    }

                } else if (collisionEvent.entity2.getComponent(AsteroidComponent.class) != null) {
                    AsteroidComponent asteroidComponentParent = collisionEvent.entity2.getComponent(AsteroidComponent.class);
                    PositionComponent positionComponentParent = collisionEvent.entity2.getComponent(PositionComponent.class);
                    if (asteroidComponentParent.splitsLeft == 0) {
                        collisionEvent.entity2.removeThis = true;
                        collisionEvent.entity1.removeThis = true;
                        NotifyDestruction(new AsteroidDestructionEvent(collisionEvent.entity1));

                    } else {
                        int newSplitsLeft = asteroidComponentParent.splitsLeft - 1;
                        int newSplitsInto = asteroidComponentParent.splitsInto;
                        for (int i = 0; i < newSplitsInto; i++) {
                            AsteroidEntity entity = new AsteroidEntity(asteroidComponentParent.scale * decreasePerSplit, positionComponentParent.position.x, positionComponentParent.position.y, Helpers.getRandomVector(), newSplitsLeft, newSplitsInto);
                            world.addEntity(entity);
                        }

                        collisionEvent.entity1.removeThis = true;
                        collisionEvent.entity2.removeThis = true;
                        NotifyDestruction(new AsteroidDestructionEvent(collisionEvent.entity1));
                    }
                }
            }
        }
    }

    @Override
    public void update(float dt) {

    }

    public void NotifyDestruction(AsteroidDestructionEvent asteroidDestructionEvent) {
        EventBus.getInstance().notifySubscribers(asteroidDestructionEvent);
    }
}
