package com.fabiangabor.porszivo.commands;

import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.domain.Direction;

public class VacuumStop implements VacuumCommand{
    private final VacuumReceiver vacuumReceiver;
    private final Datastore datastore;

    public VacuumStop(VacuumReceiver vacuumReceiver, Datastore datastore) {
        this.vacuumReceiver = vacuumReceiver;
        this.datastore = datastore;
    }

    @Override
    public void execute() {
        vacuumReceiver.stop(this, datastore, Direction.STOP);
    }

    @Override
    public String toString() {
        return Direction.STOP.toString();
    }
}
