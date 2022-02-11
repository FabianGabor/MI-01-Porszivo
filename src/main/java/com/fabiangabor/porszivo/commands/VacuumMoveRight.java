package com.fabiangabor.porszivo.commands;

import com.fabiangabor.porszivo.domain.Direction;
import com.fabiangabor.porszivo.data.Datastore;

public class VacuumMoveRight implements VacuumCommand {
    private final VacuumReceiver vacuumReceiver;
    private final Datastore datastore;

    public VacuumMoveRight(VacuumReceiver vacuumReceiver, Datastore datastore) {
        this.vacuumReceiver = vacuumReceiver;
        this.datastore = datastore;
    }

    @Override
    public void execute() {
        vacuumReceiver.moveRight(this, datastore, Direction.RIGHT);
    }

    @Override
    public String toString() {
        return Direction.RIGHT.toString();
    }
}