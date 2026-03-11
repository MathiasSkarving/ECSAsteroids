package dk.sdu.cbse.enemy;

import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.common.ecs.World;

public class EnemyPlugin implements IGamePlugin {
    @Override
    public void start(World world) {
        world.addSystem(new EnemySpawningSystem());
        world.addSystem(new EnemyShootingSystem());
    }

    @Override
    public void stop(World world) {

    }
}
