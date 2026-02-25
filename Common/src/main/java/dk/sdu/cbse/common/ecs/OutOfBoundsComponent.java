package dk.sdu.cbse.common.ecs;

public class OutOfBoundsComponent extends Component{
    public enum OutOfBoundsAction {
        REMOVE,
        WRAP,
    }

    public OutOfBoundsAction outOfBoundsAction;
}
