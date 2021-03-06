package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.service.VacuumController;
import com.fabiangabor.porszivo.view.View;

import javax.inject.Inject;


public final class App {

    @Inject private boolean isSilent;

    public void play(VacuumController controller, View view) {
        if (!isSilent) {
            view.println("Rooms before: " + controller.getDatastore().getWorld());
        }

        controller.start();

        if (!isSilent) {
            view.printDirection(controller.getDatastore());
        }
        if (!isSilent) {
            view.println("Rooms after : " + controller.getDatastore().getWorld());
            view.println("========================================");
        }
    }
}
