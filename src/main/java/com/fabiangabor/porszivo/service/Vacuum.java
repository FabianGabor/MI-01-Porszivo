package com.fabiangabor.porszivo.service;

import com.fabiangabor.porszivo.commands.VacuumCommand;
import com.fabiangabor.porszivo.commands.VacuumReceiver;
import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.domain.Direction;

public class Vacuum implements VacuumReceiver {

    private int decreasePointsAmount;
    private int increasePointsAmount;
    private final boolean silent;

    public Vacuum(boolean silent) {
        this.silent = silent;
        //this.decreasePointsAmount = 1;
        //this.increasePointsAmount = 3;

    }

    public Vacuum(boolean silent, int decreasePointsAmount, int increasePointsAmount) {
        this(silent);
        this.decreasePointsAmount = decreasePointsAmount;
        this.increasePointsAmount = increasePointsAmount;
    }

    @Override
    public boolean isSilent() {
        return silent;
    }

    @Override
    public void moveLeft(VacuumCommand command, Datastore datastore, Direction direction) {
        datastore.setDirection(direction);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
        datastore.increaseRoomNumber(direction.getVal());
        datastore.decreasePoints(decreasePointsAmount);
    }

    @Override
    public void moveRight(VacuumCommand command, Datastore datastore, Direction direction) {
        datastore.setDirection(direction);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
        datastore.increaseRoomNumber(direction.getVal());
        datastore.decreasePoints(decreasePointsAmount);
    }

    @Override
    public void clean(VacuumCommand command, Datastore datastore) {
        datastore.setRoomClean();
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
        datastore.increasePoints(increasePointsAmount);
    }

    @Override
    public void stop(VacuumCommand command, Datastore datastore, Direction direction) {
        datastore.setDirection(direction);
        datastore.addToCommandHistory(command);
        datastore.addToDirectionHistory(command);
    }
}
