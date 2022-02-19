package com.fabiangabor.porszivo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fabiangabor.porszivo.commands.VacuumReceiver;
import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.data.MemoryDatastore;
import com.fabiangabor.porszivo.data.Points;
import com.fabiangabor.porszivo.domain.World;
import com.fabiangabor.porszivo.domain.WorldFactory;
import com.fabiangabor.porszivo.service.Vacuum;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.LogView;
import com.fabiangabor.porszivo.view.View;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;


public class App {
    static final int WORLDS = 2;
    static final int WORLD_SIZE = 5;
    static final boolean SILENT = false;

    public static void main(String[] args) {
        View view = new LogView();
        List<VacuumController> controllers = new ArrayList<>();

        for (int i = 0; i < WORLDS; i++) {
            World world = new WorldFactory().create(WORLD_SIZE);

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

    @Inject View view;
    @Inject VacuumController controller;
    @Inject VacuumReceiver vacuum;
    Datastore datastore;
    @Inject int roomNumber;

    void play() {

        List<VacuumController> controllers = new ArrayList<>();

        for (int i = 0; i < WORLDS; i++) {


            view.println("Rooms: " + controller.getDatastore().getWorld());
            controllers.add(controller);
            controller.start();
            view.printDirection(controller.getDatastore());
            view.println("Rooms: " + controller.getDatastore().getWorld());
        }
    }
}
