package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;

import java.util.ArrayList;
import java.util.List;

public class CircleCollisionSystem extends BaseSystem implements IGamePlugin {
    public CircleCollisionSystem() {
    }

    @Override
    public void update(float dt) {
        List<Entity> entityList = new ArrayList<>(world.getEntitiesWith(PositionComponent.class, CircleColliderComponent.class));

        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i).removeThis) continue;
            for (int j = i + 1; j < entityList.size(); j++) {
                if (entityList.get(j).removeThis) continue;
                PositionComponent positionComponent1 = entityList.get(i).getComponent(PositionComponent.class);
                CircleColliderComponent circleColliderComponent1 = entityList.get(i).getComponent(CircleColliderComponent.class);
                PositionComponent positionComponent2 = entityList.get(j).getComponent(PositionComponent.class);
                CircleColliderComponent circleColliderComponent2 = entityList.get(j).getComponent(CircleColliderComponent.class);

                double distanceBetween = Helpers.calculateDistance(positionComponent1, positionComponent2);
                if (distanceBetween < circleColliderComponent2.radius + circleColliderComponent1.radius) {
                    NotifyCollision(new CollisionEvent(entityList.get(i), entityList.get(j)));
                }
            }
        }
    }

    public void NotifyCollision(CollisionEvent collisionEvent) {
        EventBus.getInstance().notifySubscribers(collisionEvent);
    }

    @Override
    public void start(World world) {
        world.addSystem(this);
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 0;
    }
}
