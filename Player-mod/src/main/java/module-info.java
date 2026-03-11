import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    requires Common;
    requires CommonEnemy;
    requires CommonBullet;

    provides IGamePlugin with PlayerPlugin;
}
