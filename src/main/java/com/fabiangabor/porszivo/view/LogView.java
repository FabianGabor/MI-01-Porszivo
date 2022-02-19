package com.fabiangabor.porszivo.view;

import com.fabiangabor.porszivo.commands.VacuumCommand;
import com.fabiangabor.porszivo.data.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogView implements View{
    private static final Logger LOG = LoggerFactory.getLogger(LogView.class);

    @Override
    public void println(Object string) {
        LOG.info(string.toString());
    }

    @Override
    public void printDirection(Datastore datastore) {
        for (String direction : datastore.getDirectionHistory()) {
            println(direction);
        }
    }

    @Override
    public void printCommandHistory(Datastore datastore) {
        for (VacuumCommand command : datastore.getCommandHistory()) {
            println(command);
        }
    }
}
