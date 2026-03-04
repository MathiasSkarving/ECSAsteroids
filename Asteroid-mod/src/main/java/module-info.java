import dk.sdu.cbse.asteroid.AsteroidPlugin;
import dk.sdu.cbse.common.ecs.IGamePlugin;

module Asteroid.mod {
    requires Common;

    provides IGamePlugin with AsteroidPlugin;
}