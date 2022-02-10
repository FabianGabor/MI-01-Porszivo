package com.fabiangabor.porszivo.commands;

import com.fabiangabor.porszivo.Direction;

public class VacuumMoveLeft implements VacuumCommand {
    private final VacuumReceiver vacuumReceiver;

    public VacuumMoveLeft(VacuumReceiver vacuumReceiver) {
        this.vacuumReceiver = vacuumReceiver;
    }

    @Override
    public void execute() {
        vacuumReceiver.moveLeft();
    }

    @Override
    public String toString() {
        return Direction.LEFT.toString();
    }
}