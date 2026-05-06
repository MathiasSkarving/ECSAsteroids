package dk.sdu.cbse.core;

import javafx.scene.image.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

@Configuration
@PropertySource("classpath:game.properties")
public class BackgroundConfig
{
    @Bean
    Image background(@Value("${background-image}") String url) {
        return new Image(Objects.requireNonNull(
                Objects.requireNonNull(getClass().getResource("/" + url)).toExternalForm()
        ));
    }
}
