package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.Component;

public class ScoreComponent extends Component {
    public int scoreForPlayerId;
    public double score = 0;

    public ScoreComponent(int scoreForPlayerId, double score) {
        this.scoreForPlayerId = scoreForPlayerId;
        this.score = score;
    }
}
