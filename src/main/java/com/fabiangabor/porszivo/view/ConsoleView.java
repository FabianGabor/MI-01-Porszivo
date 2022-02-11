package com.fabiangabor.porszivo.view;

import com.fabiangabor.porszivo.commands.VacuumCommand;
import com.fabiangabor.porszivo.data.Datastore;

public class ConsoleView implements View{
    @Override
    public void println(Object string) {
        System.out.println(string);
    }

    @Override
    public void printDirection(Datastore datastore) {
        for (String direction : datastore.getDirectionHistory()) {
            System.out.println(direction);
        }
    }

    @Override
    public void printCommandHistory(Datastore datastore) {
        for (VacuumCommand command : datastore.getCommandHistory()) {
            System.out.println(command);
        }
    }
}
