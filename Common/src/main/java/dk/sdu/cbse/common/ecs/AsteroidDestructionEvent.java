package dk.sdu.cbse.common.ecs;

public class AsteroidDestructionEvent extends EventType {
    public Entity desctructionResponsible;

    public AsteroidDestructionEvent(Entity desctructionResponsible) {
        this.desctructionResponsible = desctructionResponsible;
    }
}
