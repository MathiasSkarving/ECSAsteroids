import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.score.ScorePlugin;

module Score.mod {
    requires Common;

    provides IGamePlugin with ScorePlugin;
}