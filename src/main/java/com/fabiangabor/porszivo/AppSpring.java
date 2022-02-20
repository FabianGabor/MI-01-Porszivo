package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.data.Config;
import com.fabiangabor.porszivo.data.Points;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.View;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class AppSpring {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class)) {

            App app = appContext.getBean(App.class);
            View view = appContext.getBean(View.class);
            List<VacuumController> controllers = new ArrayList<>();
            Config config = appContext.getBean(Config.class);

            for (int i = 0; i < config.getWorldsCount(); i++) {
                VacuumController controller = appContext.getBean(VacuumController.class);
                controllers.add(controller);
                app.play(controller, view);
            }
            view.println("Average points: " + Points.calcAveragePoints(controllers));
        }
    }
}
