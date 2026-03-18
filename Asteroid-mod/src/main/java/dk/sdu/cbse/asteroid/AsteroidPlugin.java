package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.common.ecs.World;

public class AsteroidPlugin implements IGamePlugin {
    @Override
    public void start(World world) {
        System.out.println("Started asteroid");
        world.addSystem(new AsteroidCollisionSystem());
        world.addSystem(new AsteroidSpawningSystem());
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 4;
    }
}
