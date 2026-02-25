package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.common.ecs.World;

public class PlayerPlugin implements IGamePlugin {
    @Override
    public void start(World world) {
        PlayerEntity player = new PlayerEntity();
        world.addEntity(player);
        world.addSystem(new PlayerSystem());
    }

    @Override
    public void stop(World world) {

    }
}
