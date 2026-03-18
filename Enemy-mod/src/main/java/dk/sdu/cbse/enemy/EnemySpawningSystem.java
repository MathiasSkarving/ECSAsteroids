package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.ecs.*;

import java.util.Random;

public class EnemySpawningSystem extends BaseSystem {
    double spawnInterval = 8000;
    double lastSpawn;

    @Override
    public void update(float dt) {
        double now = (double) System.nanoTime() / 1000000;

        if (now - lastSpawn > spawnInterval) {
            Vector2 startVel = Helpers.getRandomVector().scale(1);

            EnemyEntity entity = new EnemyEntity(new Vector2(), startVel, "FF0000");
            double spawnX = 0;
            double spawnY = 0;
            Random rand = new Random();
            int edge = (int) Math.floor(rand.nextDouble() * 4);
            switch (edge) {
                case 0: // Top
                    spawnX = world.getWorldWidth() * rand.nextDouble();
                    spawnY = -entity.getComponent(CircleColliderComponent.class).radius * 2;
                    break;
                case 1: // Left
                    spawnX = -entity.getComponent(CircleColliderComponent.class).radius * 2;
                    spawnY = world.getWorldHeight() * rand.nextDouble();
                    break;
                case 2: // Right
                    spawnX = world.getWorldWidth() + entity.getComponent(CircleColliderComponent.class).radius * 2;
                    spawnY = world.getWorldHeight() * rand.nextDouble();
                    break;
                case 3: // Bottom
                    spawnX = world.getWorldWidth() * rand.nextDouble();
                    spawnY = world.getWorldHeight() + entity.getComponent(CircleColliderComponent.class).radius * 2;
                    break;
            }
            entity.getComponent(PositionComponent.class).position = new Vector2(spawnX, spawnY);
            world.addEntity(entity);
            lastSpawn = now;
        }
    }
}
