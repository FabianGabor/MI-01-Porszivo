package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.commands.VacuumReceiver;
import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.data.MemoryDatastore;
import com.fabiangabor.porszivo.domain.WorldFactory;
import com.fabiangabor.porszivo.service.Vacuum;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.LogView;
import com.fabiangabor.porszivo.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;


@Configuration
@PropertySource("classpath:config.properties")
@ComponentScan(basePackages = "com.fabiangabor.porszivo")
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    @Bean App app() {
        return new App();
    }

    @Bean View view() {
        return new LogView();
    }

    @Bean
    @Scope("prototype")
    VacuumController controller() {
        LOG.debug("VacuumController controller()");
        return new VacuumController(new Vacuum(true), new MemoryDatastore(new WorldFactory().create(4), roomNumber()), 0);
    }

    @Bean
    VacuumReceiver vacuumReceiver() {
        return new Vacuum(true);
    }

    @Bean
    @Scope("prototype")
    Datastore datastore() {
        return new MemoryDatastore(new WorldFactory().create(4), roomNumber());
    }

    @Bean
    int roomNumber() {
        return 0;
    }
}