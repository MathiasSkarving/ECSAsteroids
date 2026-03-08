package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.*;

import java.util.HashSet;

public class ScoreSystem extends BaseSystem implements Subscriber {
    public ScoreSystem() {
        EventBus.getInstance().subscribe(this, AsteroidDestructionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {
        if(event.getClass() != AsteroidDestructionEvent.class) return;
        AsteroidDestructionEvent asteroidDestructionEvent = (AsteroidDestructionEvent)event;
        OwnedByComponent playerResponsibleComponent;
        try {
            playerResponsibleComponent = asteroidDestructionEvent.desctructionResponsible.getComponent(OwnedByComponent.class);
        } catch(NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
        if(playerResponsibleComponent == null) return;
        if(playerResponsibleComponent.e.getComponent(PlayerComponent.class) == null) return;

        HashSet<Entity> entities = world.getEntitiesWith(ScoreComponent.class, TextComponent.class);
        for (Entity e : entities) {
            if(e.getComponent(ScoreComponent.class) != null) {
                ScoreComponent score = e.getComponent(ScoreComponent.class);
                PlayerComponent playerComponent = playerResponsibleComponent.e.getComponent(PlayerComponent.class);
                if(score.scoreForPlayerId == playerComponent.playerId) {
                    score.score += 100 * (1/asteroidDestructionEvent.asteroidSize);
                    TextComponent textComponent = e.getComponent(TextComponent.class);
                    if(textComponent != null) {
                        textComponent.text = "Player " + playerComponent.playerId + ": " + String.format("%.0f", score.score);
                    }
                }
            }
        }

    }

    @Override
    public void update(float dt) {

    }
}
