package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.*;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class Game implements Subscriber {
    List<IGamePlugin> pluginsList;
    InputHandler handler;
    Stage stage;
    AnimationTimer gameLoop;
    int width, height;
    GraphicsContext gc;


    @Autowired
    public Game(List<IGamePlugin> plugins) {
        this.pluginsList = new java.util.ArrayList<>(plugins);
    }

    public void startGame(Stage stage) {
        this.stage = stage;
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = (int) size.getWidth();
        this.height = (int) size.getHeight();
        Group root = new Group();
        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);
        javafx.scene.canvas.Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        this.gc = canvas.getGraphicsContext2D();
        handler = new InputHandler(gameScene);
        refreshGame();
    }

    public void refreshGame() {
        // Stop plugins on the current world so they can clean up before reset
        World current = null;
        try {
            current = World.getInstance();
        } catch (Exception ignored) {}
        if (current != null) {
            for (IGamePlugin plugin : pluginsList) {
                try { plugin.stop(current); } catch (Exception ex) { System.out.println("Error stopping plugin " + plugin.getClass().getName() + ": " + ex.getMessage()); }
            }
        }

        // Reset the world (this recreates EventBus)
        World.getInstance().reset();
        // Re-subscribe Game to restart event as EventBus was recreated
        EventBus.getInstance().subscribe(this, GameRestartEvent.class);

        // Ensure the new world has the canvas GraphicsContext and dimensions
        World.getInstance().setWorldHeight(this.height);
        World.getInstance().setWorldWidth(this.width);
        World.getInstance().setGraphicsContext(this.gc);

        // Start plugins on the fresh world
        Collections.sort(pluginsList);
        for (IGamePlugin plugin : pluginsList) {
            try { plugin.start(World.getInstance()); } catch (Exception ex) { System.out.println("Error starting plugin " + plugin.getClass().getName() + ": " + ex.getMessage()); ex.printStackTrace(); }
        }

        renderGame();
    }

    public void renderGame() {
        if (gameLoop != null) {
            gameLoop.stop();
        }

        gameLoop = new AnimationTimer() {
            private long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                float deltaTime = (now - lastTime) / 1_000_000_000f;
                lastTime = now;
                World.getInstance().update(deltaTime);
            }
        };

        gameLoop.start();
    }

    @Override
    public void onEvent(EventType event) {
            refreshGame();
            System.out.println("Game restarted");
    }
}
