package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.commonbullet.BulletComponent;

import java.util.HashSet;

public class ScoreSystem extends BaseSystem implements Subscriber {
    public ScoreSystem() {
        EventBus.getInstance().subscribe(this, AsteroidDestructionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {
        System.out.println("ScoreSystem got event: " + event.getClass());
        if (event.getClass() != AsteroidDestructionEvent.class) return;
        AsteroidDestructionEvent asteroidDestructionEvent = (AsteroidDestructionEvent) event;
        BulletComponent bulletResponsibleComponent;
        bulletResponsibleComponent = asteroidDestructionEvent.desctructionResponsible.getComponent(BulletComponent.class);

        if (bulletResponsibleComponent == null) {
            System.out.println("Bullet is null");
            return;
        }
        if (bulletResponsibleComponent.owner.getComponent(PlayerComponent.class) == null) {
            System.out.println("Player responsible is not a player");
            return;
        }
        HashSet<Entity> entities = world.getEntitiesWith(ScoreComponent.class, TextComponent.class);
        for (Entity e : entities) {
            if (e.getComponent(ScoreComponent.class) != null) {
                ScoreComponent score = e.getComponent(ScoreComponent.class);
                PlayerComponent playerComponent = bulletResponsibleComponent.owner.getComponent(PlayerComponent.class);
                System.out.println("Player responsible" + playerComponent.playerId);
                if (score.scoreForPlayerId == playerComponent.playerId) {
                    score.score += 100 * (1f / (float)asteroidDestructionEvent.asteroidSize);
                    TextComponent textComponent = e.getComponent(TextComponent.class);
                    if (textComponent != null) {
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
