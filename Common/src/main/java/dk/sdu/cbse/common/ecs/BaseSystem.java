package dk.sdu.cbse.common.ecs;

public abstract class BaseSystem {
    protected World world;

    public void update(float dt) {

    }

    public void setWorld(World world) {
        this.world = world;
    }
}
