package com.fabiangabor.porszivo;

import java.util.ArrayList;
import java.util.List;

import com.fabiangabor.porszivo.data.Points;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;


public class App {

    @Inject private int worldsCount;
    @Inject private int worldSize;
    @Inject private boolean silent;
    @Inject View view;
    @Inject VacuumController controller;

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public void play() {
        LOG.info("Vacuum is silent: {}", silent);
        LOG.info("Worlds count: {}", worldsCount);
        LOG.info("World size: {}", worldSize);

        List<VacuumController> controllers = new ArrayList<>();

        for (int i = 0; i < worldsCount; i++) {
            controllers.add(controller);
            controller.start();
        }

        view.println("Average points: " + Points.calcAveragePoints(controllers));
    }
}
