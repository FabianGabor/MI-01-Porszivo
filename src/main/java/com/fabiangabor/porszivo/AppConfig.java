package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.commands.VacuumReceiver;
import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.data.Points;
import com.fabiangabor.porszivo.domain.World;
import com.fabiangabor.porszivo.domain.WorldFactory;
import com.fabiangabor.porszivo.service.Vacuum;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.ConsoleView;
import com.fabiangabor.porszivo.view.LoggerView;
import com.fabiangabor.porszivo.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.inject.Named;
import java.util.Random;

@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan(basePackages = "com.fabiangabor.porszivo")
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    @Value("${silent}")
    boolean silent;

    @Value("#{T(Integer).parseInt('${worldsCount}')}")
    int worldsCount;

    @Value("#{T(Integer).parseInt('${worldSize}')}")
    int worldSize;

    private final Random rand = new Random();

    @Bean
    @Scope("prototype")
    @Qualifier("random")
    int random() {
        return rand.nextInt(worldSize - 1);
    }

    @Bean boolean isSilent() {
        return silent;
    }

    @Bean int worldsCount() {
        return worldsCount;
    }

    @Bean int worldSize() {
        return worldSize;
    }

    @Bean App app() {
        return new App();
    }

    @Bean View view() {
        return new LoggerView();
    }

    @Bean
    VacuumReceiver vacuum() {
        return new Vacuum(silent);
    }

    @Bean
    @Scope("prototype")
    World worldFactory(int worldSize) {
        return new WorldFactory().create(worldSize);
    }

    @Bean
    Datastore datastore() {
        return new Datastore(new WorldFactory().create(worldSize));
    }

    @Bean
    @Scope("prototype")
    VacuumController controller() {
        return new VacuumController(vacuum(), datastore(), random());
    }

    @Bean
    Points points() {
        return new Points();
    }
}
