package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;
import dk.sdu.cbse.commonbullet.BulletComponent;
import java.util.HashSet;

public class ShootingSystem extends BaseSystem implements Subscriber {
    private final double bulletSpeed;
    private final double bulletRadius;
    private final double bulletAirTime;
    private final double bulletOffTheMapDistanceBeforeRemoval;
    private final String color;
    private final int resolution;

    public ShootingSystem(double bulletRadius, double bulletSpeed, double bulletAirTime, double bulletOffTheMapDistanceBeforeRemoval, String color, int resolution) {
        EventBus.getInstance().subscribe(this, ShootingEvent.class);
        this.bulletRadius = bulletRadius;
        this.bulletSpeed = bulletSpeed;
        this.bulletAirTime = bulletAirTime;
        this.bulletOffTheMapDistanceBeforeRemoval = bulletOffTheMapDistanceBeforeRemoval;
        this.color = color;
        this.resolution = resolution;
    }

    @Override
    public void onEvent(EventType event) {
        if (event.getClass() == ShootingEvent.class) {
            ShootingEvent shootingEvent = (ShootingEvent) event;
            Entity source = shootingEvent.source;
            RotationComponent rotationComponent;
            PositionComponent positionComponent;
            if(source.getComponent(RotationComponent.class) == null) {
                return;
            }
            if(source.getComponent(PositionComponent.class) == null) {
                return;
            }
            rotationComponent = source.getComponent(RotationComponent.class);
            positionComponent = source.getComponent(PositionComponent.class);
            Vector2 bulletVelocity = new Vector2(rotationComponent.angle + rotationComponent.angleOffset);
            bulletVelocity = bulletVelocity.scale(bulletSpeed);
            world.addEntity(new BulletEntity(bulletRadius, bulletVelocity, positionComponent.position, source, bulletAirTime, bulletOffTheMapDistanceBeforeRemoval, resolution, color));
        }
    }

    @Override
    public void update(float dt) {
        HashSet<Entity> entities;
        entities = world.getEntitiesWith(BulletComponent.class, TimerComponent.class);
        double now = (double) java.lang.System.nanoTime() / 1000000;
        for(Entity e : entities) {
            TimerComponent timerComponent = e.getComponent(TimerComponent.class);
            if(now - timerComponent.startTime > timerComponent.millisInterval) {
                e.removeThis = true;
            }
        }
    }
}
