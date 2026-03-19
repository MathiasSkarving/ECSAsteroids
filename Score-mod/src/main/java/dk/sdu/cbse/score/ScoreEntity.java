package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.*;
import javafx.scene.text.Font;

public class ScoreEntity extends Entity {
    public ScoreEntity(int playerId, Vector2 scorePosition, Font font, Offset verticalOffset, Offset horizontalOffset, String color) {
        addComponent(new ScoreComponent(playerId, 0));
        addComponent(new TextComponent("Player " + playerId + ": 0", font, color, verticalOffset, horizontalOffset));
        addComponent(new PositionComponent(scorePosition));
    }


}
