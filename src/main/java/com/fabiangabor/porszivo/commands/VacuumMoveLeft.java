package com.fabiangabor.porszivo.commands;

import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.domain.Direction;

public class VacuumMoveLeft implements VacuumCommand {
    private final VacuumReceiver vacuumReceiver;
    private final Datastore datastore;

    public VacuumMoveLeft(VacuumReceiver vacuumReceiver, Datastore datastore) {
        this.vacuumReceiver = vacuumReceiver;
        this.datastore = datastore;
    }

    @Override
    public void execute() {
        vacuumReceiver.moveLeft(this, datastore, Direction.LEFT);
    }


    @Override
    public String toString() {
        return Direction.LEFT.toString();
    }
}
