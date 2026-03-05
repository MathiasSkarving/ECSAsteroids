package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.ecs.*;

import java.util.Random;

public class AsteroidEntity extends Entity {
    public AsteroidEntity(double scale, double spawnX, double spawnY, Vector2 startVelocity, int splitsLeft, int splitsInto) {
        addComponent(new CircleColliderComponent());
        addComponent(new RotationComponent());
        addComponent(new PositionComponent(new Vector2(spawnX, spawnY)));
        addComponent(new VelocityComponent(startVelocity));
        addComponent(new RenderComponent());
        addComponent(new OutOfBoundsComponent());
        addComponent(new AsteroidComponent(splitsLeft, splitsInto, scale));

        RenderComponent renderComponent = getComponent(RenderComponent.class);
        int numEdges = 4 + (int) (Math.random() * 10);
        double angleIncrement = (double) 360 / numEdges;

        renderComponent.vertices = new Vector2[numEdges];
        for (int i = 0; i < numEdges; i++) {
            double range = scale * (30 + Math.random() * 50);
            renderComponent.vertices[i] = new Vector2(Math.cos(Math.toRadians(angleIncrement * i)), Math.sin(Math.toRadians(angleIncrement * i))).scale(range);
        }

        renderComponent.fillColor = "000000";

        OutOfBoundsComponent outOfBoundsComponent = getComponent(OutOfBoundsComponent.class);
        outOfBoundsComponent.outOfBoundsAction = OutOfBoundsComponent.OutOfBoundsAction.BOUNCE;

        CircleColliderComponent circleColliderComponent = getComponent(CircleColliderComponent.class);
        circleColliderComponent.radius = Helpers.calculateColliderRadiusFromVertices(renderComponent.vertices);
    }
}
