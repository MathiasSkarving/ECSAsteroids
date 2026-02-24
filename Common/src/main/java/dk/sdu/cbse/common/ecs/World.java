package dk.sdu.cbse.common.ecs;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;

public class World {
    Map<Class<? extends Component>, List<Entity>> componentEntityMap = new HashMap<>();
    List<Entity> entities = new ArrayList<>();
    List<System> systems = new ArrayList<>();
    int worldWidth;
    int worldHeight;
    public InputHandler inputHandler = new InputHandler();
    public GraphicsContext gc;

    public World(int width, int height, GraphicsContext gc) {
        worldHeight = height;
        worldWidth = width;
        this.gc = gc;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addComponent(Entity entity, Component component) {
        entity.addComponent(component);
        if (!componentEntityMap.containsKey(component.getClass())) {
            componentEntityMap.put(component.getClass(), new ArrayList<>());
        }
        componentEntityMap.get(component.getClass()).add(entity);
    }

    public void addSystem(System system) {
        system.setWorld(this);
        systems.add(system);
    }

    public final List<Entity> getEntitiesWith(Class<? extends Component>... componentTypes) {
        List<Entity> result = componentEntityMap.getOrDefault(componentTypes[0], new ArrayList<>());
        for (int i = 1; i < componentTypes.length; i++) {
            result.retainAll(componentEntityMap.getOrDefault(componentTypes[i], new ArrayList<>()));
        }
        return result;
    }

    public void update(float dt) {
        for(System s : systems) {
            s.update(dt);
        }
    }
}
