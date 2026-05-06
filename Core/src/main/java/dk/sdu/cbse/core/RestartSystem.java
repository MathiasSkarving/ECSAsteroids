package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ServiceLoader;

@Component
public class RestartSystem extends BaseSystem implements Subscriber, IGamePlugin {

    @Override
    public void onEvent(EventType event) {
        KeyEvent keyEvent = (KeyEvent)event;
        if(keyEvent.getKey() == GameKey.ESCAPE && keyEvent.getPressed()) {
            EventBus.getInstance().notifySubscribers(new GameRestartEvent());
        }
    }

    @Override
    public void start(World world) {
        world.addSystem(this);
        EventBus.getInstance().subscribe(this, KeyEvent.class);
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 0;
    }
}
