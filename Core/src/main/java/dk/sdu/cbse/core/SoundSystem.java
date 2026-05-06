package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import javafx.scene.media.*;

import java.util.Objects;


public class SoundSystem extends BaseSystem implements Subscriber, IGamePlugin {
    @Override
    public void start(World world) {
        world.addSystem(this);
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 0;
    }

    private final Media shootingMedia = new Media(Objects.requireNonNull(getClass().getResource("/Pew.wav")).toExternalForm());

    public SoundSystem() {
        EventBus.getInstance().subscribe(this, ShootingEvent.class);
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
    }

    @Override
    public void onEvent(EventType event) {
        if (event.getClass() == ShootingEvent.class) {
            MediaPlayer player = new MediaPlayer(shootingMedia);
            player.setOnEndOfMedia(player::dispose); // clean up after playing
            player.play();
        }
    }
}
