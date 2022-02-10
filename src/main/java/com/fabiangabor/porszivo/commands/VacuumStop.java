package com.fabiangabor.porszivo.commands;

import com.fabiangabor.porszivo.Direction;

public class VacuumStop implements VacuumCommand{
    private final VacuumReceiver vacuumReceiver;

    public VacuumStop(VacuumReceiver vacuumReceiver) {
        this.vacuumReceiver = vacuumReceiver;
    }

    @Override
    public void execute() {
        vacuumReceiver.stop();
    }

    @Override
    public String toString() {
        return Direction.STOP.toString();
    }
}
