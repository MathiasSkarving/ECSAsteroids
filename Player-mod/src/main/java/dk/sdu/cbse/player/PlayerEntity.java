package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;

public class PlayerEntity extends Entity {
    public PlayerEntity() {
        addComponent(new VelocityComponent());
        addComponent(new PositionComponent());
        addComponent(new RenderComponent());
        addComponent(new RotationComponent());
        addComponent(new PlayerComponent());
        addComponent(new OutOfBoundsComponent());

        RenderComponent rendCom = getComponent(RenderComponent.class);
        rendCom.xPoints = new double[] {20, 30, 40};
        rendCom.yPoints = new double[] {20, 60, 20};

        Helpers.centerPoints(rendCom.xPoints, rendCom.yPoints);

        rendCom.hexColor = "005000";

        VelocityComponent velCom = getComponent(VelocityComponent.class);
        velCom.directionVel = 0;
        velCom.velX = 0;
        velCom.velY = 0;

        PositionComponent posCom = getComponent(PositionComponent.class);
        posCom.x = 500;
        posCom.y = 500;

        OutOfBoundsComponent outBound = getComponent(OutOfBoundsComponent.class);
        outBound.outOfBoundsAction = OutOfBoundsComponent.OutOfBoundsAction.WRAP;
    }
}
