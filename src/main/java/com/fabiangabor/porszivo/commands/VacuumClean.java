package com.fabiangabor.porszivo.commands;

public class VacuumClean implements VacuumCommand {
    private final VacuumReceiver vacuumReceiver;

    public VacuumClean(VacuumReceiver vacuumReceiver) {
        this.vacuumReceiver = vacuumReceiver;
    }

    @Override
    public void execute() {
        vacuumReceiver.clean();
    }

    @Override
    public String toString() {
        return "CLEAN";
    }
}
