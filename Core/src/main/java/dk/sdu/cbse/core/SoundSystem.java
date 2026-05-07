package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import javafx.scene.media.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SoundSystem extends BaseSystem implements Subscriber, IGamePlugin {

    @Override
    public void start(World world) {
        world.addSystem(this);
        EventBus.getInstance().subscribe(this, ShootingEvent.class);
        EventBus.getInstance().subscribe(this, CollisionEvent.class);
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 0;
    }


    private Media shootingMedia;

    @Override
    public void onEvent(EventType event) {
        if (event.getClass() == ShootingEvent.class) {
            if (shootingMedia == null) {
                System.out.println("SoundSystem: shootingMedia is null, cannot play sound");
                return;
            }
            MediaPlayer player = new MediaPlayer(shootingMedia);
            player.setOnEndOfMedia(player::dispose);
            player.play();
        }
    }

    @Autowired
    public void setBulletSound(Media bulletSound) {
        shootingMedia = bulletSound;
        System.out.println("SoundSystem: Bullet sound set");
    }

}
