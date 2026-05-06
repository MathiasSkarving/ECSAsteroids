import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.core.*;

module Core {
    uses dk.sdu.cbse.common.ecs.IGamePlugin;
    requires javafx.controls;
    requires javafx.graphics;
    requires Common;
    requires java.desktop;
    requires javafx.media;
    requires spring.core;
    requires spring.context;
    requires spring.beans;

    opens dk.sdu.cbse.core to spring.core, spring.context, spring.beans;
    exports dk.sdu.cbse.core;

    provides IGamePlugin with CircleCollisionSystem, MovingSystem, OutOfBoundsSystem, RemoveEntitySystem, RenderSystem, RestartSystem, RotationSystem, SoundSystem;
}
