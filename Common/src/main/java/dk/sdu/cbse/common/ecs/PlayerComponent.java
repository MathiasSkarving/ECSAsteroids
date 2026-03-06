package dk.sdu.cbse.common.ecs;

import java.util.HashMap;

public class PlayerComponent extends Component {
    public int playerId;
    public HashMap<GameAction, GameKey> gameActionGameKeyHashMap;
    public double shootingInterval = 0;

    public PlayerComponent(int id, HashMap<GameAction, GameKey> gameActionGameKeyHashMap) {
        playerId = id;
        this.gameActionGameKeyHashMap = gameActionGameKeyHashMap;
    }
}
