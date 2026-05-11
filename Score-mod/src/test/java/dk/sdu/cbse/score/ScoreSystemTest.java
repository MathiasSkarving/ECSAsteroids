package dk.sdu.cbse.score;

import dk.sdu.cbse.bullet.BulletEntity;
import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.commonbullet.BulletComponent;
import dk.sdu.cbse.player.PlayerEntity;
import javafx.scene.text.Font;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScoreSystemTest {
    @Mock
    ScoreService mockService;
    ScoreSystem scoreSystem;

    @BeforeEach
    void setUp() {
        scoreSystem = new ScoreSystem(mockService);
        scoreSystem.setWorld(World.getInstance());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testServiceCalled() {
        when(mockService.incrementAndGetScore(eq(anyInt()), eq(anyInt()))).thenReturn(eq(anyInt()));
        Entity player = new PlayerEntity("000000", 1, new HashMap<>(), 0, 0, 0);
        Entity bullet = new BulletEntity(0,new Vector2(),new Vector2(), player, 0, 0,0, "000000");
        bullet.addComponent(new OwnedByComponent(player));

        AsteroidDestructionEvent event = new AsteroidDestructionEvent(bullet, anyInt());

        scoreSystem.onEvent(event);

        verify(mockService, times(1)).incrementAndGetScore(eq(anyInt()), eq(anyInt()));
    }

    @Test
    void testServiceCalledWithCorrectPoints() {
        int asteroidSize = 100;
        int expectedPointsAdded = scoreSystem.convertAsteroidSizeToPoints(asteroidSize);

        when(mockService.incrementAndGetScore(anyInt(), eq(expectedPointsAdded))).thenReturn(expectedPointsAdded);
        Entity player = new PlayerEntity("000000", 1, new HashMap<>(), 0, 0, 0);
        PlayerComponent playerComponent = player.getComponent(PlayerComponent.class);
        Entity bullet = new BulletEntity(0,new Vector2(),new Vector2(), player, 0, 0,0, "000000");
        bullet.addComponent(new OwnedByComponent(player));

        AsteroidDestructionEvent event = new AsteroidDestructionEvent(bullet, asteroidSize);

        scoreSystem.onEvent(event);

        verify(mockService, times(1)).incrementAndGetScore(eq(playerComponent.playerId), eq(expectedPointsAdded));
    }

    @Test
    void testUIUpdatesWithReturnedValueFromService() {
        // 1. Arrange
        int playerId = 1;
        int pointsToReturn = 999; // The "fake" score returned by our API

        // Tell the mock: when called, return exactly 999
        when(mockService.incrementAndGetScore(anyInt(), anyInt())).thenReturn(pointsToReturn);

        Entity player = new PlayerEntity("000000", playerId, new HashMap<>(), 0, 0, 0);
        Entity bullet = new BulletEntity(0, new Vector2(), new Vector2(), player, 0, 0, 0, "000000");
        BulletComponent bc = new BulletComponent();
        bc.owner = player;
        bullet.addComponent(bc);
        ScoreEntity score = new ScoreEntity(playerId, new Vector2(0,0), new Font("Arial", 20), Offset.CENTER, Offset.LEFT, "0000FF");
        TextComponent textComp = score.getComponent(TextComponent.class);

        World.getInstance().addEntity(score);

        AsteroidDestructionEvent event = new AsteroidDestructionEvent(bullet, 1);

        // 2. Act
        scoreSystem.onEvent(event);

        // 3. Assert
        // Verify that the TextComponent was updated with the value the service returned
        assertEquals("Player " + playerId + ": " + pointsToReturn, textComp.text);
    }
}