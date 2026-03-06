package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.Component;

public class ScoreComponent extends Component {
    int scoreForPlayerId;
    double score;

    public ScoreComponent(int scoreForPlayerId, double score) {
        this.scoreForPlayerId = scoreForPlayerId;
        this.score = score;
    }
}
