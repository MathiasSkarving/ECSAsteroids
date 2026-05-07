package dk.sdu.cbse.core;

import dk.sdu.cbse.common.ecs.IGamePlugin;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;

import java.util.List;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

@Configuration
@ComponentScan(basePackages = {"dk.sdu.cbse"})
@Import(ResourceConfig.class)
public class ModuleConfig {
    public ModuleConfig() {
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Game game(AutowireCapableBeanFactory beanFactory) {
        return new Game(gamePlugins(beanFactory));
    }

    @Bean
    public List<IGamePlugin> gamePlugins(AutowireCapableBeanFactory beanFactory) {
        return ServiceLoader.load(IGamePlugin.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .peek(beanFactory::autowireBean)
                .peek(plugin -> beanFactory.initializeBean(plugin, plugin.getClass().getName()))
                .collect(toList());
    }
}
