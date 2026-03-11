package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ServiceLoader;

public class RestartSystem extends BaseSystem implements Subscriber {
    Game game;
    public RestartSystem(Game game) {
        EventBus.getInstance().subscribe(this, KeyEvent.class);
        this.game = game;
    }

    @Override
    public void onEvent(EventType event) {
        KeyEvent keyEvent = (KeyEvent)event;
        if(keyEvent.getKey() == GameKey.ESCAPE && keyEvent.getPressed()) {
            game.resetGame();
        }
    }
}
