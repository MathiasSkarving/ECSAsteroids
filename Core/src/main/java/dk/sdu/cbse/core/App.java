package dk.sdu.cbse.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
        game = new Game(width, height);
    }

    public static void main(String[] args) {
        launch(args);
    }

}