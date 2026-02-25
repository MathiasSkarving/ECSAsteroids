package dk.sdu.cbse.common.ecs;

import java.util.*;

public class World {
    Map<Class<? extends Component>, List<Entity>> componentEntityMap = new HashMap<>();
    List<System> systems = new ArrayList<>();
    public int worldWidth;
    public int worldHeight;

    public World(int width, int height) {
        worldHeight = height;
        worldWidth = width;
    }

    public void addEntity(Entity entity) {
        for(Component c : entity.getComponents()) {
            componentEntityMap
                    .computeIfAbsent(c.getClass(), k -> new ArrayList<>())
                    .add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        for (Component c : entity.getComponents()) {
            List<Entity> list = componentEntityMap.get(c.getClass());
            if (list != null) {
                list.remove(entity);
            }
        }
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

    @SafeVarargs
    public final List<Entity> getEntitiesWith(Class<? extends Component>... componentTypes) {
        List<Entity> result = componentEntityMap.getOrDefault(componentTypes[0], new ArrayList<>());
        for (int i = 1; i < componentTypes.length; i++) {
            result.retainAll(componentEntityMap.getOrDefault(componentTypes[i], new ArrayList<>()));
        }
        return result;
    }

    public final List<Entity> getEntitiesWith() {
        HashSet<Entity> entities = new HashSet<>();
        for(List<Entity> e : componentEntityMap.values()) {
            entities.addAll(e);
        }
        return entities.stream().toList();
    }

    public void update(float dt) {
        for(System s : systems) {
            s.update(dt);
        }
    }
}
