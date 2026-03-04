package dk.sdu.cbse.common.ecs;

public class KeyEvent extends EventType {
    private GameKey key;
    private boolean pressed;

    public KeyEvent(GameKey key, boolean pressed) {
        this.key = key;
        this.pressed = pressed;
    }

    public GameKey getKey() {
        return key;
    }
    public boolean getPressed() {
        return pressed;
    }

}
