package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;


public class Game {
    public World world;

    public Game(int width, int height) {
        world = new World(width, height);
    }


}
