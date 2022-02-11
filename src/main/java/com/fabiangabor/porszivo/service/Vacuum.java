package com.fabiangabor.porszivo.service;

import com.fabiangabor.porszivo.domain.Direction;
import com.fabiangabor.porszivo.commands.*;
import com.fabiangabor.porszivo.data.Datastore;

public class Vacuum implements VacuumReceiver {

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
        datastore.decreasePoints(1);
    }

    @Override
    public void clean(VacuumCommand command, Datastore datastore) {
        datastore.getWorld().getRoom(datastore.getRoomNumber()).setClean(true);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
        datastore.increasePoints(3);
    }

    @Override
    public void stop(VacuumCommand command, Datastore datastore, Direction direction) {
        datastore.setDirection(direction);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
    }
}