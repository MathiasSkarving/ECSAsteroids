package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.ecs.*;

public class BulletEntity extends Entity {
    public BulletEntity(double radius, Vector2 startVelocity, Vector2 startPosition, Entity source) {
        addComponent(new VelocityComponent(new Vector2(0,0)));
        VelocityComponent velocityComponent = getComponent(VelocityComponent.class);
        velocityComponent.velocity = startVelocity;

        addComponent(new PositionComponent(new Vector2(0,0)));
        PositionComponent positionComponent = getComponent(PositionComponent.class);
        positionComponent.position = startPosition;

        addComponent(new RenderComponent());
        RenderComponent renderComponent = getComponent(RenderComponent.class);
        renderComponent.vertices = Helpers.makeCircle(radius, 30);
        renderComponent.fillColor = "EEEEEE";
        Helpers.centerPoints(renderComponent.vertices);

        addComponent(new CircleColliderComponent());
        CircleColliderComponent circleColliderComponent = getComponent(CircleColliderComponent.class);
        circleColliderComponent.radius = Helpers.calculateColliderRadiusFromVertices(renderComponent.vertices);

        addComponent(new BulletComponent());
        addComponent(new RotationComponent());

        addComponent(new OutOfBoundsComponent());
        OutOfBoundsComponent outOfBoundsComponent = getComponent(OutOfBoundsComponent.class);
        outOfBoundsComponent.outOfBoundsAction = OutOfBoundsComponent.OutOfBoundsAction.BOUNCE;

        addComponent(new TimerComponent(3000));

        addComponent(new OwnedByComponent(source));
    }
}
