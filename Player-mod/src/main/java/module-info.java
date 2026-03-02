import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.player.PlayerPlugin;

module Player {
    requires Common;
    requires jdk.jfr;

    provides IGamePlugin with PlayerPlugin;
}
