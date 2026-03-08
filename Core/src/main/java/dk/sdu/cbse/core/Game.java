package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import javafx.scene.canvas.GraphicsContext;


public class Game {
    public World world;

    public Game(int width, int height, GraphicsContext gc) {
        world = World.getInstance();
        world.setWorldHeight(height);
        world.setWorldWidth(width);
    }
}
