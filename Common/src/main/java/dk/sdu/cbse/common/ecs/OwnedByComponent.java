package dk.sdu.cbse.common.ecs;

public class OwnedByComponent extends Component{
    Entity e;
    public OwnedByComponent(Entity e) {
        this.e = e;
    }
}
