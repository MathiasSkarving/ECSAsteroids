package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.commonenemy.EnemyComponent;

public class EnemyEntity extends Entity {
    public double lastShot = 0;

    public EnemyEntity(Vector2 spawnPosition, Vector2 spawnVelocity, String color) {
        addComponent(new PositionComponent(spawnPosition));
        addComponent(new VelocityComponent(spawnVelocity));

        Vector2[] vertices = Helpers.makeCircle(20, 20);

        addComponent(new RenderComponent(vertices, color));
        addComponent(new RotationComponent(0));
        addComponent(new CircleColliderComponent());
        addComponent(new OutOfBoundsComponent(OutOfBoundsComponent.OutOfBoundsAction.WRAP, 250));
        addComponent(new EnemyComponent());

        RenderComponent renderComponent = getComponent(RenderComponent.class);
        CircleColliderComponent circleColliderComponent = getComponent(CircleColliderComponent.class);

        circleColliderComponent.radius = Helpers.calculateColliderRadiusFromVertices(renderComponent.vertices);}
}
