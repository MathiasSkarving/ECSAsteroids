package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.Entity;

public class ScoreEntity extends Entity {
    public ScoreEntity(int playerId) {
        addComponent(new ScoreComponent(playerId, 0));
    }


}
