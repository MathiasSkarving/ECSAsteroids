package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;

import java.util.HashMap;

public class PlayerEntity extends Entity {
    public double lastShot = 0;

    public PlayerEntity(String hexColor, int id, HashMap<GameAction, GameKey> gameActionGameKeyHashMap, double spawnX, double spawnY, double angleOffset) {
        addComponent(new VelocityComponent(new Vector2(0,0)));
        addComponent(new PositionComponent(new Vector2(spawnX, spawnY)));
        addComponent(new RenderComponent());
        addComponent(new RotationComponent());
        addComponent(new PlayerComponent(id, gameActionGameKeyHashMap));
        addComponent(new OutOfBoundsComponent(OutOfBoundsComponent.OutOfBoundsAction.BOUNCE, 0));
        addComponent(new CircleColliderComponent());
        addComponent(new AccelerationComponent(new Vector2(0,0)));
        addComponent(new RotationalVelocityComponent(0));
        addComponent(new RotationalAccelerationComponent(0));

        RenderComponent rendCom = getComponent(RenderComponent.class);

        rendCom.vertices = new Vector2[3];
        rendCom.vertices[0] = new Vector2(40,40);
        rendCom.vertices[1] = new Vector2(60,95);
        rendCom.vertices[2] = new Vector2(80,40);
        RotationComponent rotationComponent = getComponent(RotationComponent.class);
        rotationComponent.angleOffset = angleOffset;
        Helpers.centerPoints(rendCom.vertices);
        rendCom.fillColor = hexColor;
        OutOfBoundsComponent outBound = getComponent(OutOfBoundsComponent.class);
        outBound.outOfBoundsAction = OutOfBoundsComponent.OutOfBoundsAction.BOUNCE;
        CircleColliderComponent circleColliderComponent = getComponent(CircleColliderComponent.class);
        circleColliderComponent.radius = Helpers.calculateColliderRadiusFromVertices(rendCom.vertices);
    }
}
