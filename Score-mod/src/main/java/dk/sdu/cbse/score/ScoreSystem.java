package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.AsteroidDestructionEvent;
import dk.sdu.cbse.common.ecs.EventBus;
import dk.sdu.cbse.common.ecs.EventType;
import dk.sdu.cbse.common.ecs.Subscriber;

public class ScoreSystem implements Subscriber {
    public ScoreSystem() {
        EventBus.getInstance().subscribe(this, AsteroidDestructionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {

    }
}
