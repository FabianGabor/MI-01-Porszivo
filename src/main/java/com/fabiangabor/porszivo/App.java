package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.commands.*;
import com.fabiangabor.porszivo.service.Vacuum;
import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.world.World;
import com.fabiangabor.porszivo.world.WorldFactory;

import java.util.Random;

public class App {
    static final int WORLDS = 1;
    static final int WORLD_SIZE = 3;

    public static void main(String[] args) {

        double averagePoints = 0;

        for (int i = 0; i < WORLDS; i++) {
            //World world = new WorldFactory().create(WORLD_SIZE);
            World world = new World();
            Room room1 = new Room(false);
            Room room2 = new Room(false);
            world.addRoom(room1);
            world.addRoom(room2);

            System.out.println(world);

            VacuumReceiver vacuum = new Vacuum(false);
            VacuumController controller = new VacuumController(world, vacuum, new Random().nextInt(WORLD_SIZE - 1));

            controller.start();
            averagePoints += controller.getPoints();

            System.out.println(world);
        }

        System.out.println("Average points: " + averagePoints / WORLDS);
    }
}