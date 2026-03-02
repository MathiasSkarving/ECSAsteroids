package dk.sdu.cbse.common.ecs;

public class OutOfBoundsComponent extends Component{
    public enum OutOfBoundsAction {
        REMOVE,
        WRAP,
        BOUNCE,
    }

    public OutOfBoundsAction outOfBoundsAction;
}
