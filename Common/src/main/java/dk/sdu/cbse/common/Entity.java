package dk.sdu.cbse.common;

import java.util.HashSet;

public abstract class Entity {
    public HashSet<Component> components;

    public <T extends Component> T getComponent(Class<T> componentType) {
        for(Component c : components) {
            if (componentType.isInstance(c)) {
                return componentType.cast(c);
            }
        }
        return null;
    }
}
