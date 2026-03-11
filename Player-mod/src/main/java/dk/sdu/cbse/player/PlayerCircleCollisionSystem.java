package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;

import java.util.ArrayList;

public class PlayerCircleCollisionSystem extends BaseSystem implements Subscriber {

    public PlayerCircleCollisionSystem() {
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {
        Entity collisionEntity1 = ((CollisionEvent)event).entity1;
        Entity collisionEntity2 = ((CollisionEvent)event).entity2;

        ArrayList<Entity> worldPlayers = new ArrayList<>(world.getEntitiesWith(dk.sdu.cbse.common.ecs.PlayerComponent.class, PositionComponent.class, CircleColliderComponent.class));
        if(worldPlayers.contains(collisionEntity1) && worldPlayers.contains(collisionEntity2)) {
            VelocityComponent v1 = collisionEntity1.getComponent(VelocityComponent.class);
            VelocityComponent v2 = collisionEntity2.getComponent(VelocityComponent.class);
            PositionComponent p1 = collisionEntity1.getComponent(PositionComponent.class);
            PositionComponent p2 = collisionEntity2.getComponent(PositionComponent.class);
            CircleColliderComponent c1 = collisionEntity1.getComponent(CircleColliderComponent.class);
            CircleColliderComponent c2 = collisionEntity2.getComponent(CircleColliderComponent.class);

            double overlap = (p1.position.subtract(p2.position)).magnitude() - (c1.radius + c2.radius);
            Vector2 collisionNormal = p1.position.subtract(p2.position).normalize();

            if(overlap < 0) {
                p1.position = p1.position.subtract(collisionNormal.scale(overlap/2));
                p2.position = p2.position.add(collisionNormal.scale(overlap/2));
            }
            Vector2 OldVel = v1.velocity;
            v1.velocity = v2.velocity;
            v2.velocity = OldVel;
        }
    }

    @Override
    public void update(float dt) {

    }
}
