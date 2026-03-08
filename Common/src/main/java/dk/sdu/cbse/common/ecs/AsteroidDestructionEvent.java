package dk.sdu.cbse.common.ecs;

public class AsteroidDestructionEvent extends EventType {
    public Entity desctructionResponsible;
    public double asteroidSize;

    public AsteroidDestructionEvent(Entity desctructionResponsible, double asteroidSize) {
        this.desctructionResponsible = desctructionResponsible;
        this.asteroidSize = asteroidSize;
    }
}
