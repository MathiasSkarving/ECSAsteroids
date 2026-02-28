package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.GameAction;
import dk.sdu.cbse.common.ecs.GameKey;
import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.common.ecs.World;

import java.util.HashMap;

public class PlayerPlugin implements IGamePlugin {
    HashMap<GameAction, GameKey> player1Controls = new HashMap<>();
    HashMap<GameAction, GameKey> player2Controls = new HashMap<>();

    public PlayerPlugin() {
        player1Controls.put(GameAction.Accelerate, GameKey.W);
        player1Controls.put(GameAction.RotateLeft, GameKey.A);
        player1Controls.put(GameAction.RotateRight, GameKey.D);
        player1Controls.put(GameAction.Shoot, GameKey.SPACE);

        player2Controls.put(GameAction.Accelerate, GameKey.UP);
        player2Controls.put(GameAction.RotateLeft, GameKey.LEFT);
        player2Controls.put(GameAction.RotateRight, GameKey.RIGHT);
        player2Controls.put(GameAction.Shoot, GameKey.ENTER);
    }

    @Override
    public void start(World world) {
        PlayerEntity player1 = new PlayerEntity("0000FF", 1, player1Controls, 200, 200, 90);
        PlayerEntity player2 = new PlayerEntity("00FF00",2, player2Controls, 600, 600, 90);
        world.addEntity(player1);
        world.addEntity(player2);
        world.addSystem(new PlayerMovementSystem());
        world.addSystem(new PlayerCircleCollisionSystem());
    }

    @Override
    public void stop(World world) {

    }
}
