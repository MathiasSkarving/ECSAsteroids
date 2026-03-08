package dk.sdu.cbse.common.ecs;

public class OwnedByComponent extends Component{
    public Entity e;
    public OwnedByComponent(Entity e) {
        this.e = e;
    }
}
