package dk.sdu.cbse.common.ecs;

public class TimerComponent extends Component{
    public double millisInterval;
    public double startTime = 0;

    public TimerComponent(double millisInterval) {
        this.millisInterval = millisInterval;
        this.startTime = (double) java.lang.System.nanoTime() / 1000000;
    }
}
