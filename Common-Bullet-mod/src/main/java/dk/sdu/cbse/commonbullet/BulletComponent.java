package dk.sdu.cbse.commonbullet;

import dk.sdu.cbse.common.ecs.Component;
import dk.sdu.cbse.common.ecs.Entity;

public class BulletComponent extends Component {
    public Entity owner;

    public BulletComponent(Entity owner) {
        this.owner = owner;
    }

    public BulletComponent() {

    }
}
