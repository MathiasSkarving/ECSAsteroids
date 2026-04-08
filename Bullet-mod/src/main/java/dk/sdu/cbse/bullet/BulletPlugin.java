package dk.sdu.cbse.bullet;

import dk.sdu.cbse.common.ecs.IGamePlugin;
import dk.sdu.cbse.common.ecs.World;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BulletPlugin implements IGamePlugin {
    private AnnotationConfigApplicationContext context;

    @Override
    public void start(World world) {
        context = new AnnotationConfigApplicationContext(BulletConfig.class);
        world.addSystem(context.getBean(ShootingSystem.class));
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 2;
    }
}
