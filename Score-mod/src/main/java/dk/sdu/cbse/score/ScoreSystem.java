package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.commonbullet.BulletComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

public class ScoreSystem extends BaseSystem implements Subscriber, IGamePlugin {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ScoreService scoreService;

    public ScoreSystem() {
    }


    @Override
    public void onEvent(EventType event) {
        if(scoreService == null) {
            System.out.println("ScoreSystem: scoreService is null, cannot update score");
            return;
        }

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
        PlayerComponent player = bulletResponsibleComponent.owner.getComponent(PlayerComponent.class);
        int playerId = player.playerId;
        int pointsToAdd = (int) (100 * (1f / (float) asteroidDestructionEvent.asteroidSize));

        int newScore = scoreService.incrementAndGetScore(playerId, pointsToAdd);
        if (newScore == -1) {
            System.out.println("ScoreService returned -1, cannot update score");
            return;
        }

        for (Entity e : entities) {
            ScoreComponent score = e.getComponent(ScoreComponent.class);
            if (score.scoreForPlayerId == playerId) {
                TextComponent text = e.getComponent(TextComponent.class);
                if (text != null) {
                    text.text = "Player " + playerId + ": " + newScore;
                }
            }
        }
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void start(World world) {
        world.addSystem(this);
        EventBus.getInstance().subscribe(this, AsteroidDestructionEvent.class);
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 0;
    }
}
