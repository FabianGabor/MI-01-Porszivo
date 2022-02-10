package com.fabiangabor.porszivo.commands;

import com.fabiangabor.porszivo.Direction;

public class VacuumMoveRight implements VacuumCommand {
    private final VacuumReceiver vacuumReceiver;

    public VacuumMoveRight(VacuumReceiver vacuumReceiver) {
        this.vacuumReceiver = vacuumReceiver;
    }

    @Override
    public void execute() {
        vacuumReceiver.moveRight();
    }

    @Override
    public String toString() {
        return Direction.RIGHT.toString();
    }
}
