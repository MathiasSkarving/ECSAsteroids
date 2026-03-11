package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;

import java.util.HashSet;

public class OutOfBoundsSystem extends BaseSystem {
    public OutOfBoundsSystem() {
    }

    @Override
    public void update(float dt) {
        HashSet<Entity> entities = world.getEntitiesWith(OutOfBoundsComponent.class, PositionComponent.class);
        for(Entity e : entities) {
            OutOfBoundsComponent outOfBoundsComponent = e.getComponent(OutOfBoundsComponent.class);
            if(outOfBoundsComponent.outOfBoundsAction == OutOfBoundsComponent.OutOfBoundsAction.WRAP) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                if(pos.position.x > world.getWorldWidth() + outOfBoundsComponent.howMuchCanIGoOffTheMap) {
                    pos.position.x = -outOfBoundsComponent.howMuchCanIGoOffTheMap;
                }
                if(pos.position.x < -outOfBoundsComponent.howMuchCanIGoOffTheMap) {
                    pos.position.x = world.getWorldWidth() + outOfBoundsComponent.howMuchCanIGoOffTheMap;
                }
                if(pos.position.y > world.getWorldHeight() + outOfBoundsComponent.howMuchCanIGoOffTheMap) {
                    pos.position.y = -outOfBoundsComponent.howMuchCanIGoOffTheMap;
                }
                if(pos.position.y < -outOfBoundsComponent.howMuchCanIGoOffTheMap) {
                    pos.position.y = world.getWorldHeight() + outOfBoundsComponent.howMuchCanIGoOffTheMap;
                }
            }
            else if(outOfBoundsComponent.outOfBoundsAction == OutOfBoundsComponent.OutOfBoundsAction.REMOVE) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                if(pos.position.x > world.getWorldWidth() + outOfBoundsComponent.howMuchCanIGoOffTheMap) {
                    e.removeThis = true;
                }
                if(pos.position.x < -outOfBoundsComponent.howMuchCanIGoOffTheMap) {
                    e.removeThis = true;
                }
                if(pos.position.y > world.getWorldHeight() + outOfBoundsComponent.howMuchCanIGoOffTheMap) {
                    e.removeThis = true;
                }
                if(pos.position.y < -outOfBoundsComponent.howMuchCanIGoOffTheMap) {
                    e.removeThis = true;
                }
            }
            else if(e.getComponent(CircleColliderComponent.class) != null && e.getComponent(VelocityComponent.class) != null && outOfBoundsComponent.outOfBoundsAction == OutOfBoundsComponent.OutOfBoundsAction.BOUNCE) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                CircleColliderComponent circleColliderComponent = e.getComponent(CircleColliderComponent.class);
                VelocityComponent velocityComponent = e.getComponent(VelocityComponent.class);

                if(pos.position.x > world.getWorldWidth() - circleColliderComponent.radius) {
                    pos.position.x = world.getWorldWidth() - circleColliderComponent.radius;
                    velocityComponent.velocity.x = velocityComponent.velocity.x * -1;
                }
                if(pos.position.x < circleColliderComponent.radius) {
                    pos.position.x = circleColliderComponent.radius;
                    velocityComponent.velocity.x = velocityComponent.velocity.x * -1;
                }
                if(pos.position.y > world.getWorldHeight() - circleColliderComponent.radius) {
                    pos.position.y = world.getWorldHeight() - circleColliderComponent.radius;
                    velocityComponent.velocity.y = velocityComponent.velocity.y * -1;
                }
                if(pos.position.y < circleColliderComponent.radius) {
                    pos.position.y = circleColliderComponent.radius;
                    velocityComponent.velocity.y = velocityComponent.velocity.y * -1;
                }
            }
        }
    }
}
