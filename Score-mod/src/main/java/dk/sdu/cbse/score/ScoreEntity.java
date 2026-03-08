package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.Entity;
import dk.sdu.cbse.common.ecs.PositionComponent;
import dk.sdu.cbse.common.ecs.TextComponent;
import dk.sdu.cbse.common.ecs.Vector2;

public class ScoreEntity extends Entity {
    public ScoreEntity(int playerId, Vector2 scorePosition) {
        addComponent(new ScoreComponent(playerId, 0));
        addComponent(new TextComponent("Player " + playerId + ": " + 0, "FFFFFF", 38));
        addComponent(new PositionComponent(scorePosition));
    }


}
