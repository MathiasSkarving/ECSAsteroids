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
                case W -> keyPressed(new KeyEvent(GameKey.W, true));
                case S -> keyPressed(new KeyEvent(GameKey.S, true));
                case A -> keyPressed(new KeyEvent(GameKey.A, true));
                case D -> keyPressed(new KeyEvent(GameKey.D, true));
                case ENTER -> keyPressed(new KeyEvent(GameKey.FIRE2, true));
            }
        });

        gameScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP -> keyReleased(new KeyEvent(GameKey.UP, false));
                case DOWN -> keyReleased(new KeyEvent(GameKey.DOWN, false));
                case LEFT -> keyReleased(new KeyEvent(GameKey.LEFT, false));
                case RIGHT -> keyReleased(new KeyEvent(GameKey.RIGHT, false));
                case SPACE -> keyReleased(new KeyEvent(GameKey.FIRE, false));
                case W -> keyReleased(new KeyEvent(GameKey.W, false));
                case S -> keyReleased(new KeyEvent(GameKey.S, false));
                case A -> keyReleased(new KeyEvent(GameKey.A, false));
                case D -> keyReleased(new KeyEvent(GameKey.D, false));
                case ENTER -> keyReleased(new KeyEvent(GameKey.FIRE2, false));
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
