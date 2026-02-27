package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.ArrayList;
import java.util.List;

public class CircleCollisionSystem extends System {
    public CircleCollisionSystem(World world) {
        this.world = world;
    }

    @Override
    public void update(float dt) {
        List<Entity> entityList = new ArrayList<>(world.getEntitiesWith(PositionComponent.class, CircleColliderComponent.class));

        for (int i = 0; i < entityList.size(); i++) {
            for (int j = i + 1; j < entityList.size(); j++) {
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
}
