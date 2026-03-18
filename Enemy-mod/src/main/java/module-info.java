import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.enemy.EnemyPlugin;

module Enemy {
    requires Common;
    requires CommonEnemy;
    requires CommonBullet;
    provides IGamePlugin with EnemyPlugin;
}