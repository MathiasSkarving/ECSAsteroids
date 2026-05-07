package dk.sdu.cbse.core;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

@Configuration
@PropertySource("classpath:game.properties")
public class ResourceConfig
{
    @Bean
    Image background(@Value("${background-image}") String url) {
        return new Image(Objects.requireNonNull(
                Objects.requireNonNull(getClass().getResource("/" + url)).toExternalForm()
        ));
    }

    @Bean
    Media bulletSound(@Value("${bullet-sound}") String url) {
        return new Media(Objects.requireNonNull(
                Objects.requireNonNull(getClass().getResource("/" + url)).toExternalForm()
        ));
    }
}
