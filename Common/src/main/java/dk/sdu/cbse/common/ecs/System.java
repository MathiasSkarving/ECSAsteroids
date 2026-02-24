package dk.sdu.cbse.common.ecs;

import java.util.HashSet;

public abstract class System {
    private World world;

    public void update(float dt) {

    }

    public void setWorld(World world) {
        this.world = world;
    }
}
