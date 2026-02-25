package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.EventBus;
import dk.sdu.cbse.common.ecs.EventType;
import dk.sdu.cbse.common.ecs.GameKey;
import dk.sdu.cbse.common.ecs.KeyEvent;import javafx.scene.Scene;

public class InputHandler {
    Scene gameScene;
    public InputHandler(Scene gameScene) {
        this.gameScene = gameScene;

        gameScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> keyPressed(new KeyEvent(GameKey.UP, true));
                case DOWN -> keyPressed(new KeyEvent(GameKey.DOWN, true));
                case LEFT -> keyPressed(new KeyEvent(GameKey.LEFT, true));
                case RIGHT -> keyPressed(new KeyEvent(GameKey.RIGHT, true));
                case SPACE -> keyPressed(new KeyEvent(GameKey.FIRE, true));
            }
        });

        gameScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP -> keyReleased(new KeyEvent(GameKey.UP, false));
                case DOWN -> keyReleased(new KeyEvent(GameKey.DOWN, false));
                case LEFT -> keyReleased(new KeyEvent(GameKey.LEFT, false));
                case RIGHT -> keyReleased(new KeyEvent(GameKey.RIGHT, false));
                case SPACE -> keyReleased(new KeyEvent(GameKey.FIRE, false));
            }
        });

    }

    public void keyPressed(KeyEvent keyEvent) {
        EventBus.getInstance().notifySubscribers(keyEvent);
    }

    public void keyReleased(KeyEvent keyEvent) {
        EventBus.getInstance().notifySubscribers(keyEvent);
    }
}
