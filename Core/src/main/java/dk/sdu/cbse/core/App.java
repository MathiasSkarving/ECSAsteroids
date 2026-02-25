package dk.sdu.cbse.core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.System;
import java.util.ServiceLoader;

import dk.sdu.cbse.common.ecs.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Game game;
    private int width = 1000;
    private int height = 1000;

    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Scene gameScene = new Scene(root);
        stage.setScene(gameScene);
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        game = new Game(width, height, gc);

        stage.show();
        stage.setTitle("AsteroidsFX");

        InputHandler handler = new InputHandler(gameScene);

        game.world.addSystem(new RenderSystem(game.world, gc));
        game.world.addSystem(new PositionSystem(game.world));
        game.world.addSystem(new OutOfBoundsSystem(game.world));

        ServiceLoader<IGamePlugin> plugins = ServiceLoader.load(IGamePlugin.class);

        for(IGamePlugin plugin : plugins){
            plugin.start(game.world);
        }

        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastTime = 0;

            @Override
            public void handle(long now) {
                float deltaTime = (now - lastTime) / 1_000_000_000f;
                lastTime = now;
                game.world.update(deltaTime);
            }
        };

        gameLoop.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}