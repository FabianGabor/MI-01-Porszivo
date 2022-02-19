package com.fabiangabor.porszivo;

import java.util.ArrayList;
import java.util.List;

import com.fabiangabor.porszivo.commands.VacuumReceiver;
import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.data.MemoryDatastore;
import com.fabiangabor.porszivo.data.Points;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.LogView;
import com.fabiangabor.porszivo.view.View;

import javax.inject.Inject;


public class App {
    static final int WORLDS = 2;
    static final int WORLD_SIZE = 3;
    static final boolean SILENT = true;

    public static void main(String[] args) {
        View view = new LogView();
        List<VacuumController> controllers = new ArrayList<>();

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

            view.println(world);

            Datastore dataStore = new MemoryDatastore(world);
            VacuumController controller = new VacuumController(new Vacuum(SILENT),
                    dataStore, new Random().nextInt(WORLD_SIZE - 1));
            controllers.add(controller);
            controller.start();

            view.printDirection(controller.getDatastore());
            view.println(world);
        }

        view.println("Average points: " + Points.calcAveragePoints(controllers));
    }

    @Inject boolean isSilent;

    void play(VacuumController controller, View view) {
        if (!isSilent) {
            view.println("Rooms before: " + controller.getDatastore().getWorld());
        }

        controller.start();

        if (!isSilent) {
            view.printDirection(controller.getDatastore());
        }
        if (!isSilent) {
            view.println("Rooms after : " + controller.getDatastore().getWorld());
        }
    }
}
