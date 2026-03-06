package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.Entity;
import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.common.ecs.PlayerComponent;
import dk.sdu.cbse.common.ecs.World;

import java.util.HashSet;

public class ScorePlugin implements IGamePlugin {
    @Override
    public void start(World world) {
        HashSet<Entity> playerEntities = world.getEntitiesWith(PlayerComponent.class);
        for(int i = 0; i<playerEntities.size(); i++) {
            Entity p = playerEntities.stream().toList().get(i);
            if(p.getComponent())
            world.addEntity(new ScoreEntity());
        }
    }

    @Override
    public void stop(World world) {

    }
}
