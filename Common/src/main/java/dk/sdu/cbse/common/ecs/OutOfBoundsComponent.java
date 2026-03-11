package dk.sdu.cbse.common.ecs;

public class OutOfBoundsComponent extends Component{
    public double howMuchCanIGoOffTheMap;

    public enum OutOfBoundsAction {
        REMOVE,
        WRAP,
        BOUNCE,
    }

    public OutOfBoundsAction outOfBoundsAction;

    public OutOfBoundsComponent(OutOfBoundsAction outOfBoundsAction, double howMuchCanIGoOffTheMap) {
        this.outOfBoundsAction = outOfBoundsAction;
        this.howMuchCanIGoOffTheMap = howMuchCanIGoOffTheMap;
    }
}
