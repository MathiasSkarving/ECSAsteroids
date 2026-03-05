package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Objects;

public class RenderSystem extends BaseSystem {
    GraphicsContext gc;
    Image background;

    public RenderSystem(World world, GraphicsContext gc, String backgroundImagePath) {
        this.world = world;
        this.gc = gc;
        background = new Image(Objects.requireNonNull(getClass().getResourceAsStream(backgroundImagePath)));
    }

    @Override
    public void update(float dt) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.drawImage(background, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        HashSet<Entity> entitiesToRender = world.getEntitiesWith(RenderComponent.class, PositionComponent.class, RotationComponent.class);
        for (Entity e : entitiesToRender) {
            RenderComponent   rc  = e.getComponent(RenderComponent.class);
            PositionComponent pos = e.getComponent(PositionComponent.class);
            RotationComponent rot = e.getComponent(RotationComponent.class);
            gc.save();
            gc.translate(pos.position.x, pos.position.y);
            gc.rotate(rot.angle);
            gc.setFill(Color.web(rc.fillColor));
            gc.fillPolygon(Helpers.calculateXpointsFromVectors(rc.vertices), Helpers.calculateYpointsFromVectors(rc.vertices), rc.vertices.length);
            gc.restore();
        }
    }
}
