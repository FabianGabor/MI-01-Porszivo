package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.commands.*;
import com.fabiangabor.porszivo.service.Vacuum;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class App {
    static final int WORLDS = 20;
    static final int WORLD_SIZE = 10;

    public static void main(String[] args) throws NoSuchAlgorithmException {

        double averagePoints = 0;

        for (int i = 0; i < WORLDS; i++) {
            World world = new World();
            world.initWorld(WORLD_SIZE);

            VacuumReceiver vacuum = new Vacuum(world, new Random().nextInt(WORLD_SIZE - 1), true);

            vacuum.start();
            averagePoints += vacuum.getPoints();
        }

        System.out.println("Average points: " + averagePoints / WORLDS);
    }
}
