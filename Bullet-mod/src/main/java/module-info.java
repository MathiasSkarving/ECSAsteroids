import dk.sdu.cbse.bullet.BulletPlugin;
import dk.sdu.cbse.common.ecs.IGamePlugin;

module Bullet {
    requires Common;
    requires CommonBullet;
    requires spring.core;
    requires spring.context;
    requires spring.beans;

    opens dk.sdu.cbse.bullet to spring.core, spring.beans, spring.context;

    provides IGamePlugin with BulletPlugin;
}