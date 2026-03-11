import dk.sdu.cbse.bullet.BulletPlugin;
import dk.sdu.cbse.common.ecs.IGamePlugin;

module Bullet {
    requires Common;
    requires CommonBullet;

    provides IGamePlugin with BulletPlugin;
}