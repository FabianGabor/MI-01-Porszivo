package com.fabiangabor.porszivo.service;

import com.fabiangabor.porszivo.commands.VacuumCommand;
import com.fabiangabor.porszivo.commands.VacuumReceiver;
import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.domain.Direction;

public class Vacuum implements VacuumReceiver {

    public static final int DECREASE_POINTS_AMOUNT = 1;
    public static final int INCREASE_POINTS_AMOUNT = 3;
    private final boolean silent;

    public Vacuum() {
        this.silent = false;
    }

    public Vacuum(boolean silent) {
        this.silent = silent;
    }

    public boolean isSilent() {
        return silent;
    }

    @Override
    public void moveLeft(VacuumCommand command, Datastore datastore, Direction direction) {
        datastore.setDirection(direction);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
        datastore.increaseRoomNumber(direction.getVal());
        datastore.decreasePoints(1);
    }

    @Override
    public void moveRight(VacuumCommand command, Datastore datastore, Direction direction) {
        datastore.setDirection(direction);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
        datastore.increaseRoomNumber(direction.getVal());
        datastore.decreasePoints(DECREASE_POINTS_AMOUNT);
    }

    @Override
    public void clean(VacuumCommand command, Datastore datastore) {
        datastore.getWorld().getRoom(datastore.getRoomNumber()).setClean(true);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
        datastore.increasePoints(INCREASE_POINTS_AMOUNT);
    }

    @Override
    public void stop(VacuumCommand command, Datastore datastore, Direction direction) {
        datastore.setDirection(direction);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
    }
}
