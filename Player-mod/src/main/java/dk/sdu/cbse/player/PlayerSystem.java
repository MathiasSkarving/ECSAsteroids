package dk.sdu.cbse.player;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;

import java.util.List;

public class PlayerSystem extends System {
    @Override
    public void update(float dt) {
        List<Entity> players = world.getEntitiesWith(PlayerComponent.class);

        Entity player = players.get(0);

        VelocityComponent velComp = player.getComponent(VelocityComponent.class);
        RotationComponent rotComp = player.getComponent(RotationComponent.class);

        if(world.inputHandler.isKeyPressed(GameKey.LEFT)) {
            rotComp.angle = rotComp.angle-(5*dt);
        }
        if(world.inputHandler.isKeyPressed(GameKey.RIGHT)) {
            rotComp.angle = rotComp.angle+(5*dt);
        }
        if(world.inputHandler.isKeyPressed(GameKey.UP)) {
            velComp.velocity = velComp.velocity+(10*dt);
        }
        if(!world.inputHandler.isKeyPressed(GameKey.UP)) {
            velComp.velocity = velComp.velocity*(0.5*dt);
        }
    }
}
