package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.common.ecs.World;

public class PlayerPlugin implements IGamePlugin {
    @Override
    public void start(World world) {
        PlayerEntity player1 = new PlayerEntity("0000FF", 1);
        PlayerEntity player2 = new PlayerEntity("00FF00",2);
        world.addEntity(player1);
        world.addEntity(player2);
        world.addSystem(new PlayerSystem());
    }

    @Override
    public void stop(World world) {

    }
}
