package dk.sdu.cbse.common.ecs;

import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class InputHandler {
    private Set<GameKey> pressedKeys = new HashSet<>();

    public void keyPressed(GameKey key) {
        pressedKeys.add(key);
    }

    public void keyReleased(GameKey key) {
        pressedKeys.remove(key);
    }

    public boolean isKeyPressed(GameKey key) {
        return pressedKeys.contains(key);
    }
}
