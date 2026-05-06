package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import dk.sdu.cbse.common.ecs.BaseSystem;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;

@Component
public class RenderSystem extends BaseSystem implements IGamePlugin {
    GraphicsContext gc;

    private Image background;

    public RenderSystem() {}

    @Override
    public void update(float dt) {
        if (gc == null) {
            return;
        }
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        if (background != null) {
            gc.drawImage(background, 0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        }

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

        HashSet<Entity> textToRender = world.getEntitiesWith(TextComponent.class, PositionComponent.class);
        for(Entity e : textToRender) {
            TextComponent     tc  = e.getComponent(TextComponent.class);
            PositionComponent pos = e.getComponent(PositionComponent.class);
            gc.save();
            gc.setFont(tc.font);
            gc.setFill(Color.web(tc.color));
            gc.fillText(tc.text, pos.position.x + tc.offsetX, pos.position.y + tc.offsetY);
            gc.restore();
        }


    }

    @Override
    public void start(World world) {
        world.addSystem(this);
        gc = world.getGraphicsContext();
    }

    @Autowired
    public void setImageBackground(Image image) {
        background = image;
    }

    @Override
    public void stop(World world) {

    }

    @Override
    public Integer getPriority() {
        return 0;
    }
}
