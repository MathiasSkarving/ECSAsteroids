package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.*;

import java.util.HashSet;

public class ScorePlugin implements IGamePlugin {
    @Override
    public void start(World world) {
        HashSet<Entity> playerEntities = world.getEntitiesWith(PlayerComponent.class);
        for(int i = 0; i<playerEntities.size(); i++) {
            System.out.println(playerEntities.getClass());
            Entity p = playerEntities.stream().toList().get(i);
            if(p.getComponent(PlayerComponent.class) != null) {
                PlayerComponent playerComponent = p.getComponent(PlayerComponent.class);
                if(playerComponent.playerId == 1) {
                    world.addEntity(new ScoreEntity(playerComponent.playerId, new Vector2(50,50)));
                } else if(playerComponent.playerId == 2) {
                    world.addEntity(new ScoreEntity(playerComponent.playerId, new Vector2(world.getWorldWidth()-400,50)));
                }
            }
        }

        world.addSystem(new ScoreSystem());
    }

    @Override
    public void stop(World world) {

    }
}
