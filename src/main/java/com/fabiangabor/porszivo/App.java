package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.commands.*;
import com.fabiangabor.porszivo.data.Points;
import com.fabiangabor.porszivo.service.Vacuum;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.domain.World;
import com.fabiangabor.porszivo.domain.WorldFactory;
import com.fabiangabor.porszivo.view.ConsoleView;
import com.fabiangabor.porszivo.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    static final int WORLDS = 20;
    static final int WORLD_SIZE = 10;

    public static void main(String[] args) {

        View consoleView = new ConsoleView();
        List<VacuumController> controllers = new ArrayList<>();

        for (int i = 0; i < WORLDS; i++) {
            World world = new WorldFactory().create(WORLD_SIZE);

            consoleView.println(world);

            VacuumReceiver vacuum = new Vacuum(false);
            VacuumController controller = new VacuumController(world, vacuum, new Random().nextInt(WORLD_SIZE - 1));
            controllers.add(controller);

            controller.start();

            consoleView.printDirection(controller.getDatastore());

            consoleView.println(world);
        }

        consoleView.println("Average points: " + Points.calcAveragePoints(controllers));
    }
}