package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import javafx.scene.media.*;

import java.util.ArrayList;
import java.util.Objects;


public class SoundSystem extends BaseSystem implements Subscriber {
    private final Media shootingMedia = new Media(Objects.requireNonNull(getClass().getResource("/dk/sdu/cbse/Pew.wav")).toExternalForm());

    public SoundSystem(World world) {
        EventBus.getInstance().subscribe(this, ShootingEvent.class);
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
        this.world = world;
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
