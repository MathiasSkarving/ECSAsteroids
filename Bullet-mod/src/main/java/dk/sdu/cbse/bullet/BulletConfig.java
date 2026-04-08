package dk.sdu.cbse.bullet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:bullet.properties")
public class BulletConfig {
    @Bean
    public ShootingSystem shootingSystem(@Value("${bullet.radius}") double bulletRadius, @Value("${bullet.speed}") double bulletSpeed, @Value("${bullet.airtime}") double shootingInterval, @Value("${bullet.outofboundsdist}") double offTheMap, @Value("${bullet.color}") String color, @Value("${bullet.resolution}") int resolution) {
        return new ShootingSystem(bulletRadius, bulletSpeed, shootingInterval, offTheMap, color, resolution);
    }
}
