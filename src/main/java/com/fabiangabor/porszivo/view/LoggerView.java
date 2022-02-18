package com.fabiangabor.porszivo.view;

import com.fabiangabor.porszivo.commands.VacuumCommand;
import com.fabiangabor.porszivo.data.Datastore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerView implements View{
    private static Logger LOG = LoggerFactory.getLogger(LoggerView.class);

    @Override
    public void println(Object string) {
        LOG.info((String) string);
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
