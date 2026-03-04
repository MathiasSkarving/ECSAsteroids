package dk.sdu.cbse.common.ecs;

public class RotationComponent extends Component {
    public double angle;
    public double angleOffset;

    public RotationComponent(double angle) {
        this.angle = angle;
    }

    public RotationComponent() {
        this.angle = 0;
    }
}
