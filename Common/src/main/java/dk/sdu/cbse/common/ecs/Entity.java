package dk.sdu.cbse.common.ecs;

import java.util.HashSet;

public abstract class Entity {
    public HashSet<Component> components = new HashSet<>();

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for(Component c : components) {
            if (componentClass.isInstance(c)) {
                return componentClass.cast(c);
            }
        }
        return null;
    }

    public HashSet<Component> getComponents() {
        return components;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass) {
        for(Component c : components) {
            if (componentClass.isInstance(c)) {
                components.remove(c);
                return;
            }
        }
    }

    public void addComponent(Component component) {
        this.components.add(component);
        component.entity = this;
    }
}
