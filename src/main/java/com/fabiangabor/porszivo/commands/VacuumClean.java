package com.fabiangabor.porszivo.commands;

import com.fabiangabor.porszivo.data.Datastore;

public class VacuumClean implements VacuumCommand {
    private final VacuumReceiver vacuumReceiver;
    private final Datastore datastore;

    public VacuumClean(VacuumReceiver vacuumReceiver, Datastore datastore) {
        this.vacuumReceiver = vacuumReceiver;
        this.datastore = datastore;
    }

    @Override
    public void execute() {
        vacuumReceiver.clean(this, datastore);
    }

    @Override
    public String toString() {
        return "CLEAN";
    }
}
