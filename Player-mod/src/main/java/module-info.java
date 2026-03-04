import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    requires Common;

    provides IGamePlugin with PlayerPlugin;
}
