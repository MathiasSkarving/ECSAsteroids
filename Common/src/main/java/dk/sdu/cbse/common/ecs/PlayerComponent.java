package dk.sdu.cbse.common.ecs;

import java.util.HashSet;

public class PlayerComponent extends Component {
    public int playerId;
    public HashSet<GameKey> gameKeySet;

    public PlayerComponent(int id) {
        playerId = id;
        gameKeySet = new HashSet<>();
    }
}
