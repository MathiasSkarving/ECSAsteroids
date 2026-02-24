package dk.sdu.cbse.core;

import dk.sdu.cbse.player.PlayerPlugin;
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

        gameScene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP -> game.world.inputHandler.keyPressed(GameKey.UP);
                case DOWN -> game.world.inputHandler.keyPressed(GameKey.DOWN);
                case LEFT -> game.world.inputHandler.keyPressed(GameKey.LEFT);
                case RIGHT -> game.world.inputHandler.keyPressed(GameKey.RIGHT);
                case SPACE -> game.world.inputHandler.keyPressed(GameKey.FIRE);
            }
        });

        gameScene.setOnKeyReleased(e -> {
            switch (e.getCode()) {
                case UP -> game.world.inputHandler.keyReleased(GameKey.UP);
                case DOWN -> game.world.inputHandler.keyReleased(GameKey.DOWN);
                case LEFT -> game.world.inputHandler.keyReleased(GameKey.LEFT);
                case RIGHT -> game.world.inputHandler.keyReleased(GameKey.RIGHT);
                case SPACE -> game.world.inputHandler.keyReleased(GameKey.FIRE);
            }
        });

        stage.show();
        stage.setTitle("AsteroidsFX");

        game.world.addSystem(new RenderSystem(game.world, gc));
        game.world.addSystem(new PositionSystem(game.world));

        PlayerPlugin player = new PlayerPlugin();
        player.start(game.world);

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