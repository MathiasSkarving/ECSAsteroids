package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.Component;
import dk.sdu.cbse.common.ecs.GameAction;
import dk.sdu.cbse.common.ecs.GameKey;

import java.util.HashMap;

public class PlayerComponent extends Component {
    public int playerId;
    public HashMap<GameAction, GameKey> gameActionGameKeyHashMap;

    public PlayerComponent(int id, HashMap<GameAction, GameKey> gameActionGameKeyHashMap) {
        playerId = id;
        this.gameActionGameKeyHashMap = gameActionGameKeyHashMap;
    }
}
