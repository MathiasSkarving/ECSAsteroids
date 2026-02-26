package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.System;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;

import java.util.HashSet;
import java.util.List;

public class RenderSystem extends System {
    GraphicsContext gc;

    public RenderSystem(World world, GraphicsContext gc) {
        this.world = world;
        this.gc = gc;
    }

    @Override
    public void update(float dt) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        HashSet<Entity> entitiesToRender = world.getEntitiesWith(RenderComponent.class, PositionComponent.class, RotationComponent.class);
        for (Entity e : entitiesToRender) {
            RenderComponent   rc  = e.getComponent(RenderComponent.class);
            PositionComponent pos = e.getComponent(PositionComponent.class);
            RotationComponent rot = e.getComponent(RotationComponent.class);

            java.lang.System.out.println("pos: " + pos.x + ", " + pos.y);
            java.lang.System.out.println("angle: " + rot.angle);
            java.lang.System.out.println("color: " + rc.hexColor);
            java.lang.System.out.println("xPoints: " + java.util.Arrays.toString(rc.xPoints));
            java.lang.System.out.println("yPoints: " + java.util.Arrays.toString(rc.yPoints));
            java.lang.System.out.println("canvas size: " + gc.getCanvas().getWidth() + "x" + gc.getCanvas().getHeight());

            gc.save();

            gc.translate(pos.x, pos.y);
            gc.rotate(rot.angle);
            gc.setFill(Color.web(rc.hexColor));
            gc.fillPolygon(rc.xPoints, rc.yPoints, rc.xPoints.length);
            gc.restore();
        }
    }
}
