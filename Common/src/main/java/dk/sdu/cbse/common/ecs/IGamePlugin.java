package dk.sdu.cbse.common.ecs;

public interface IGamePlugin extends Comparable<IGamePlugin> {
    void start(World world);
    void stop(World world);
    Integer getPriority();
    @Override default int compareTo(IGamePlugin o) {
        return getPriority().compareTo(o.getPriority());
    }
}
