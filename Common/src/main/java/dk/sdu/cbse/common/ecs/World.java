package dk.sdu.cbse.common.ecs;

import java.util.*;

public class World {
    Map<Class<? extends Component>, HashSet<Entity>> componentEntityMap = new HashMap<>();
    List<BaseSystem> baseSystems = new ArrayList<>();
    private int worldWidth;
    private int worldHeight;

    private static World instance;

    private World() {
    }

    public static World getInstance() {
        if(instance == null) {
            instance = new World();
        }
        return instance;
    }

    public void setWorldWidth(int width) {
        this.worldWidth = width;
    }

    public void setWorldHeight(int height) {
        this.worldHeight = height;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public void addEntity(Entity entity) {
        for(Component c : entity.getComponents()) {
            componentEntityMap
                    .computeIfAbsent(c.getClass(), k -> new HashSet<>())
                    .add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        for (Component c : entity.getComponents()) {
            HashSet<Entity> list = componentEntityMap.get(c.getClass());
            if (list != null) {
                list.remove(entity);
            }
        }
    }

    public void addSystem(BaseSystem baseSystem) {
        baseSystem.setWorld(this);
        baseSystems.add(baseSystem);
    }

    @SafeVarargs
    public final HashSet<Entity> getEntitiesWith(Class<? extends Component>... componentTypes) {
        HashSet<Entity> result = new HashSet<>(componentEntityMap.getOrDefault(componentTypes[0], new HashSet<>()));
        for (int i = 1; i < componentTypes.length; i++) {
            result.retainAll(componentEntityMap.getOrDefault(componentTypes[i], new HashSet<>()));
        }
        return result;
    }

    public final HashSet<Entity> getEntitiesWith() {
        HashSet<Entity> entities = new HashSet<>();
        for(HashSet<Entity> e : componentEntityMap.values()) {
            entities.addAll(e);
        }
        return entities;
    }

    public void update(float dt) {
        for(BaseSystem s : baseSystems) {
            s.update(dt);
        }
    }

    public void reset() {
        int oldWidth = worldWidth;
        int oldHeight = worldHeight;
        instance = new World();
        instance.setWorldWidth(oldWidth);
        instance.setWorldHeight(oldHeight);
        EventBus.getInstance().reset();
    }
}
