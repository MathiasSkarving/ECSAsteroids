package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.ecs.BaseSystem;
import dk.sdu.cbse.common.ecs.Helpers;
import dk.sdu.cbse.common.ecs.Vector2;

import java.util.Random;

public class AsteroidSpawningSystem extends BaseSystem {
    double spawnInterval = 6000;
    double lastSpawn;

    @Override
    public void update(float dt) {
        double now = (double) System.nanoTime() / 1000000;

        Random rand = new Random();
        double spawnX = rand.nextDouble(world.worldWidth);
        double spawnY = rand.nextDouble(world.worldHeight);

        Vector2 startVel = Helpers.getRandomVector();

        if(now-lastSpawn > spawnInterval) {
            System.out.println("Spawning");
            world.addEntity(new AsteroidEntity(1,spawnX, spawnY, startVel));
            lastSpawn = now;
        }
    }
}
