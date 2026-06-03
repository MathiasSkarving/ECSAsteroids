package dk.sdu.cbse.common.ecs;

/**
 * Interface for plugins that can be added to the game
 */
public interface IGamePlugin extends Comparable<IGamePlugin> {

    /**
     * Called when the game starts, World is injected via dependency injection
     * @param world
     */
    void start(World world);

    /**
     * Called when the game stops, World is injected via dependency injection
     * @param world
     */
    void stop(World world);

    /**
     * Gets the priority of the plugin, higher priority means it is executed first
     * @return priority
     */
    Integer getPriority();

    /**
     * Compares this plugin's priority with the specified plugin's priority.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override default int compareTo(IGamePlugin o) {
        return getPriority().compareTo(o.getPriority());
    }
}
