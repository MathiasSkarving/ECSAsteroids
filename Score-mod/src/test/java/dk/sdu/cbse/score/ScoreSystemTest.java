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

    ///  Testing that the score service is actually called
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

    ///  Testing that the score system calls the score service with the expected input.
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

    /// Testing that the text component of the Score Entity is updated with the expected values when the event is received.
    @Test
    void testUIUpdatesWithReturnedValueFromService() {
        int playerId = 1;
        int pointsToReturn = 999;

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

        scoreSystem.onEvent(event);

        assertEquals("Player " + playerId + ": " + pointsToReturn, textComp.text);
    }
}