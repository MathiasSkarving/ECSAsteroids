package dk.sdu.cbse.score;

import dk.sdu.cbse.common.ecs.AsteroidDestructionEvent;
import dk.sdu.cbse.common.ecs.Entity;
import dk.sdu.cbse.common.ecs.Offset;
import dk.sdu.cbse.common.ecs.PlayerComponent;
import dk.sdu.cbse.common.ecs.TextComponent;
import dk.sdu.cbse.common.ecs.World;
import dk.sdu.cbse.commonbullet.BulletComponent;
import javafx.scene.text.Font;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScoreSystemTest {
    World world;
    ScoreSystem scoreSystem;

    @BeforeEach
    void setUp() {
        World.getInstance().reset();
        world = World.getInstance();
        scoreSystem = new ScoreSystem();
        scoreSystem.setWorld(world);
    }

    @AfterEach
    void tearDown() {
        world.reset();
    }

    @Test
    void onEventIncreasesMatchingPlayerScoreAndUpdatesText() {
        Entity player = createPlayer(1);
        Entity bullet = createBulletOwnedBy(player);
        Entity scoreHud = createScoreEntity(1);

        world.addEntity(player);
        world.addEntity(bullet);
        world.addEntity(scoreHud);

        scoreSystem.onEvent(new AsteroidDestructionEvent(bullet, 2));

        ScoreComponent scoreComponent = scoreHud.getComponent(ScoreComponent.class);
        TextComponent textComponent = scoreHud.getComponent(TextComponent.class);

        assertNotNull(scoreComponent);
        assertNotNull(textComponent);
        assertEquals(50.0, scoreComponent.score, 0.001);
        assertEquals("Player 1: 50", textComponent.text);
    }

    @Test
    void onEventDoesNothingWhenBulletHasNoPlayerOwner() {
        Entity nonPlayerOwner = new Entity() {};
        Entity bullet = createBulletOwnedBy(nonPlayerOwner);
        Entity scoreHud = createScoreEntity(1);

        world.addEntity(nonPlayerOwner);
        world.addEntity(bullet);
        world.addEntity(scoreHud);

        scoreSystem.onEvent(new AsteroidDestructionEvent(bullet, 2));

        ScoreComponent scoreComponent = scoreHud.getComponent(ScoreComponent.class);
        TextComponent textComponent = scoreHud.getComponent(TextComponent.class);

        assertNotNull(scoreComponent);
        assertNotNull(textComponent);
        assertEquals(0.0, scoreComponent.score, 0.001);
        assertEquals("Player 1: 0", textComponent.text);
    }

    @Test
    void onEventDoesNothingForNonMatchingScoreOwner() {
        Entity player = createPlayer(1);
        Entity bullet = createBulletOwnedBy(player);
        Entity scoreHudForOtherPlayer = createScoreEntity(2);

        world.addEntity(player);
        world.addEntity(bullet);
        world.addEntity(scoreHudForOtherPlayer);

        scoreSystem.onEvent(new AsteroidDestructionEvent(bullet, 2));

        ScoreComponent scoreComponent = scoreHudForOtherPlayer.getComponent(ScoreComponent.class);
        TextComponent textComponent = scoreHudForOtherPlayer.getComponent(TextComponent.class);

        assertNotNull(scoreComponent);
        assertNotNull(textComponent);
        assertEquals(0.0, scoreComponent.score, 0.001);
        assertEquals("Player 2: 0", textComponent.text);
    }

    private Entity createPlayer(int playerId) {
        Entity player = new Entity() {};
        player.addComponent(new PlayerComponent(playerId, new HashMap<>()));
        return player;
    }

    private Entity createBulletOwnedBy(Entity owner) {
        Entity bullet = new Entity() {};
        bullet.addComponent(new BulletComponent(owner));
        return bullet;
    }

    private Entity createScoreEntity(int playerId) {
        Entity scoreEntity = new Entity() {};
        scoreEntity.addComponent(new ScoreComponent(playerId, 0));
        scoreEntity.addComponent(new TextComponent("Player " + playerId + ": 0", Font.font(12), "#FFFFFF", Offset.TOP, Offset.LEFT));
        return scoreEntity;
    }
}