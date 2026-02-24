package dk.sdu.cbse.common.ecs;

import java.util.*;

public class World {
    Map<Class<? extends Component>, List<Entity>> componentEntityMap = new HashMap<>();
    List<Entity> entities = new ArrayList<>();
    List<System> systems = new ArrayList<>();
    int worldWidth;
    int worldHeight;

    public World(int width, int height) {
        worldHeight = height;
        worldWidth = width;
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

    public void update(float dt) {
        for(System s : systems) {
            s.update(dt);
        }
    }
}
