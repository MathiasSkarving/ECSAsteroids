package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;

import java.util.Arrays;
import java.util.HashMap;

public class PlayerEntity extends Entity {
    public PlayerEntity(String hexColor, int id, HashMap<GameAction, GameKey> gameActionGameKeyHashMap, double spawnX, double spawnY, double angleOffset) {
        addComponent(new VelocityComponent(new Vector2(0,0)));
        addComponent(new PositionComponent(new Vector2(spawnX, spawnY)));
        addComponent(new RenderComponent());
        addComponent(new RotationComponent());
        addComponent(new PlayerComponent(id, gameActionGameKeyHashMap));
        addComponent(new OutOfBoundsComponent());
        addComponent(new CircleColliderComponent());
        addComponent(new AccelerationComponent(new Vector2(0,0)));

        RenderComponent rendCom = getComponent(RenderComponent.class);
        rendCom.xPoints = new double[] {20, 30, 40};
        rendCom.yPoints = new double[] {20, 60, 20};

        RotationComponent rotationComponent = getComponent(RotationComponent.class);
        rotationComponent.angleOffset = angleOffset;

        Helpers.centerPoints(rendCom.xPoints, rendCom.yPoints);

        rendCom.hexColor = hexColor;

        OutOfBoundsComponent outBound = getComponent(OutOfBoundsComponent.class);
        outBound.outOfBoundsAction = OutOfBoundsComponent.OutOfBoundsAction.WRAP;

        CircleColliderComponent circleColliderComponent = getComponent(CircleColliderComponent.class);
        double maxDist = 0;
        for (int i = 0; i < rendCom.xPoints.length; i++) {
            double dist = Math.sqrt(rendCom.xPoints[i] * rendCom.xPoints[i] + rendCom.yPoints[i] * rendCom.yPoints[i]);
            maxDist = Math.max(maxDist, dist);
        }
        circleColliderComponent.radius = maxDist;
    }
}
