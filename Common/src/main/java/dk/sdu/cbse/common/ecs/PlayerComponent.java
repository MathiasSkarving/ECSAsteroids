package dk.sdu.cbse.common.ecs;

import java.util.HashMap;
import java.util.HashSet;

public class PlayerComponent extends Component {
    public int playerId;
    public HashMap<GameAction, GameKey> gameActionGameKeyHashMap;

    public PlayerComponent(int id, HashMap<GameAction, GameKey> gameActionGameKeyHashMap) {
        playerId = id;
        this.gameActionGameKeyHashMap = gameActionGameKeyHashMap;
    }
}
