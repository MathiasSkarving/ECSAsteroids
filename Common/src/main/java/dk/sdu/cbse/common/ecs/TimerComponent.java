package dk.sdu.cbse.common.ecs;

public class TimerComponent extends Component{
    public double millisInterval;
    public double lastTick = 0;

    public TimerComponent(double millisInterval) {
        this.millisInterval = millisInterval;
    }
}
