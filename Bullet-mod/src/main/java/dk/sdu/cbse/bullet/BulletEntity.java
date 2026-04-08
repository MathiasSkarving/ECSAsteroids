package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.commonbullet.BulletComponent;

public class BulletEntity extends Entity {
    public BulletEntity(double radius, Vector2 startVelocity, Vector2 startPosition, Entity source, double timeInterval, double removeOffTheMapDistance, int resolution, String hexColorCode) {
        addComponent(new VelocityComponent(new Vector2(0,0)));
        VelocityComponent velocityComponent = getComponent(VelocityComponent.class);
        velocityComponent.velocity = startVelocity;

        addComponent(new PositionComponent(new Vector2(0,0)));
        PositionComponent positionComponent = getComponent(PositionComponent.class);
        positionComponent.position = startPosition;

        addComponent(new RenderComponent());
        RenderComponent renderComponent = getComponent(RenderComponent.class);
        renderComponent.vertices = Helpers.makeCircle(radius, resolution);
        renderComponent.fillColor = hexColorCode;
        Helpers.centerPoints(renderComponent.vertices);

        addComponent(new CircleColliderComponent());
        CircleColliderComponent circleColliderComponent = getComponent(CircleColliderComponent.class);
        circleColliderComponent.radius = Helpers.calculateColliderRadiusFromVertices(renderComponent.vertices);

        addComponent(new BulletComponent(source));
        addComponent(new RotationComponent());

        addComponent(new OutOfBoundsComponent(OutOfBoundsComponent.OutOfBoundsAction.REMOVE, removeOffTheMapDistance));

        addComponent(new TimerComponent(timeInterval));
    }
}
