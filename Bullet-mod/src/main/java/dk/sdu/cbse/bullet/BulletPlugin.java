package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.common.ecs.World;

public class BulletPlugin implements IGamePlugin {
    @Override
    public void start(World world) {
        world.addSystem(new ShootingSystem());
    }

    @Override
    public void stop(World world) {

    }
}
