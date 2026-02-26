package dk.sdu.cbse.common.ecs;

public class AccelerationComponent extends Component {
    public Vector2 acceleration;

    public AccelerationComponent(Vector2 acceleration) {
        this.acceleration = acceleration;
    }
}
