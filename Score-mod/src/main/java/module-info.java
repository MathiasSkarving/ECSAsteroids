import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.score.ScorePlugin;

module Score.mod {
    requires Common;
    requires CommonBullet;

    provides IGamePlugin with ScorePlugin;
}