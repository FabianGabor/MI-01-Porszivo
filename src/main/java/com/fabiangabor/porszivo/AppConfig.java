package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.commands.VacuumReceiver;
import com.fabiangabor.porszivo.data.Config;
import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.data.MemoryDatastore;
import com.fabiangabor.porszivo.domain.WorldFactory;
import com.fabiangabor.porszivo.service.Vacuum;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.LogView;
import com.fabiangabor.porszivo.view.View;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Random;


@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan(basePackages = "com.fabiangabor.porszivo")
public class AppConfig {
    @Value("${vacuum.silent}")
    private boolean silent;

    @Value("${worlds.count}")
    private int worldsCount;

    @Value("${world.size}")
    private int worldSize;

    @Value("${vacuum.randomRoom}")
    boolean randomRoom;

    Random r = new Random();

    @Value("${vacuum.increasePointsAmount}")
    private int increasePointsAmount;
    @Value("${vacuum.decreasePointsAmount}")
    private int decreasePointsAmount;

    @Bean
    boolean isSilent() {
        return silent;
    }

    @Bean
    Config config() {
        return new Config(silent, worldsCount, worldSize);
    }

    @Bean
    App app() {
        return new App();
    }

    @Bean
    View view() {
        return new LogView();
    }

    @Bean
    @Scope("prototype")
    VacuumController controller() {
        return new VacuumController(vacuumReceiver(), datastore(), roomNumber());
    }

    @Bean
    @Scope("prototype")
    Datastore datastore() {
        return new MemoryDatastore(new WorldFactory().create(config().getRoomCount()), roomNumber());
    }

    @Bean
    VacuumReceiver vacuumReceiver() {
        return new Vacuum(config().isSilent(), decreasePointsAmount, increasePointsAmount);
    }

    @Bean
    @Scope("prototype")
    int roomNumber() {
        if (randomRoom) {
            return r.nextInt(config().getRoomCount());
        } else {
            return 0;
        }
    }
}
