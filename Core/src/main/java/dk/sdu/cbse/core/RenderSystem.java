package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.System;
import dk.sdu.cbse.common.ecs.World;
import javafx.scene.canvas.GraphicsContext;

public class RenderSystem extends System {
    GraphicsContext gc;
    public RenderSystem(World world, GraphicsContext gc) {
        this.world = world;
        this.gc = gc;
    }


}
