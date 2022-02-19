package com.fabiangabor.porszivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.data.MemoryDatastore;
import com.fabiangabor.porszivo.data.Points;
import com.fabiangabor.porszivo.domain.World;
import com.fabiangabor.porszivo.domain.WorldFactory;
import com.fabiangabor.porszivo.service.Vacuum;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.LogView;
import com.fabiangabor.porszivo.view.View;


public class App {
    static final int WORLDS = 20;
    static final int WORLD_SIZE = 10;
    static final boolean SILENT = false;

    public static void main(String[] args) {
        View view = new LogView();
        List<VacuumController> controllers = new ArrayList<>();

        for (int i = 0; i < WORLDS; i++) {
            World world = new WorldFactory().create(WORLD_SIZE);

            view.println(world);

            Datastore dataStore = new MemoryDatastore(world);
            VacuumController controller = new VacuumController(world, new Vacuum(SILENT),
                    dataStore, new Random().nextInt(WORLD_SIZE - 1));
            controllers.add(controller);
            controller.start();

            view.printDirection(controller.getDatastore());
            view.println(world);
        }

        view.println("Average points: " + Points.calcAveragePoints(controllers));
    }
}
