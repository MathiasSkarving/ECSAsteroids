package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.ecs.*;

public class AsteroidCollisionSystem extends BaseSystem implements Subscriber {
    public AsteroidCollisionSystem() {
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {
            if(((CollisionEvent) event).entity1.getComponent(AsteroidComponent.class) != null) {
                Entity asteroidEntity = ((CollisionEvent) event).entity1;
                Entity other = ((CollisionEvent) event).entity2;
                asteroidEntity.removeThis = true;
            } else if(((CollisionEvent) event).entity2.getComponent(AsteroidComponent.class) != null) {
                Entity asteroidEntity = ((CollisionEvent) event).entity2;
                Entity other = ((CollisionEvent) event).entity1;
                asteroidEntity.removeThis = true;
            }
    }

    @Override
    public void update(float dt) {

    }
}
