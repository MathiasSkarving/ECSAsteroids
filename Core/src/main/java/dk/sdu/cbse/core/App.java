package dk.sdu.cbse.core;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

import dk.sdu.cbse.common.ecs.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.swing.text.html.ImageView;
import java.awt.*;

/**
 * JavaFX App
 */
@Component
public class App extends Application {

    private static Scene scene;
    private Game game;

    @Override
    public void start(Stage stage) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);

        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }

        Game game = ctx.getBean(Game.class);
        game.startGame(stage);
        stage.show();
        stage.setTitle("AsteroidsFX");
    }

    public static void main(String[] args) {
        launch(args);
    }
}