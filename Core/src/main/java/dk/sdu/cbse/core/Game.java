package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.ServiceLoader;


public class Game {
    GraphicsContext gc;
    public Game(int width, int height, GraphicsContext gc) {
        World.getInstance().setWorldHeight(height);
        World.getInstance().setWorldWidth(width);
        this.gc = gc;
    }

    public void resetGame() {
        World.getInstance().reset();

        World.getInstance().addSystem(new RenderSystem(gc, "/dk/sdu/cbse/bg.png"));
        World.getInstance().addSystem(new MovingSystem());
        World.getInstance().addSystem(new OutOfBoundsSystem());
        World.getInstance().addSystem(new RemoveEntitySystem());
        World.getInstance().addSystem(new CircleCollisionSystem());
        World.getInstance().addSystem(new RotationSystem());
        World.getInstance().addSystem(new SoundSystem());
        World.getInstance().addSystem(new RestartSystem(this));

        ServiceLoader<IGamePlugin> plugins = ServiceLoader.load(IGamePlugin.class);

        for(IGamePlugin plugin : plugins){
            plugin.start(World.getInstance());
        }
    }
}
