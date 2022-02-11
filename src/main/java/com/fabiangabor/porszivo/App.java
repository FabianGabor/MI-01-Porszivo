package com.fabiangabor.porszivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fabiangabor.porszivo.commands.VacuumReceiver;
import com.fabiangabor.porszivo.data.Points;
import com.fabiangabor.porszivo.domain.World;
import com.fabiangabor.porszivo.domain.WorldFactory;
import com.fabiangabor.porszivo.service.Vacuum;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.ConsoleView;
import com.fabiangabor.porszivo.view.View;


public class App {
    static final int WORLDS = 20;
    static final int WORLD_SIZE = 10;
    static final boolean SILENT = false;

    public static void main(String[] args) {
        View consoleView = new ConsoleView();
        List<VacuumController> controllers = new ArrayList<>();

        for (int i = 0; i < WORLDS; i++) {
            World world = new WorldFactory().create(WORLD_SIZE);

            consoleView.println(world);

            VacuumReceiver vacuum = new Vacuum(SILENT);
            VacuumController controller = new VacuumController(world, vacuum, new Random().nextInt(WORLD_SIZE - 1));
            controllers.add(controller);
            controller.start();

            consoleView.printDirection(controller.getDatastore());
            consoleView.println(world);
        }

        consoleView.println("Average points: " + Points.calcAveragePoints(controllers));
    }
}
