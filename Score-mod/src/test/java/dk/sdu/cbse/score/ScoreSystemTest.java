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

    }
}