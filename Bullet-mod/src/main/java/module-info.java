import dk.sdu.cbse.bullet.BulletPlugin;
import dk.sdu.cbse.common.ecs.IGamePlugin;

module Bullet {
    requires Common;

    provides IGamePlugin with BulletPlugin;
}