package com.fabiangabor.porszivo.service;

import com.fabiangabor.porszivo.Direction;
import com.fabiangabor.porszivo.World;
import com.fabiangabor.porszivo.commands.*;
import com.fabiangabor.porszivo.datastore.Datastore;

public class Vacuum implements VacuumReceiver {

    private final Datastore datastore;
    private VacuumCommand moveLeft;
    private VacuumCommand moveRight;
    private VacuumCommand stop;
    private VacuumCommand clean;
    private final boolean silent;


    public Vacuum(World world) {
        this.datastore = new Datastore(world);
        this.datastore.setRoomNumber(0);
        this.silent = false;
    }

    public Vacuum(World world, int roomNumber) {
        this.datastore = new Datastore(world, roomNumber);
        this.silent = false;
    }

    public Vacuum(World world, int roomNumber, boolean silent) {
        this.datastore = new Datastore(world, roomNumber);
        this.silent = silent;
    }

    public int getPoints() {
        return datastore.getPoints();
    }

    @Override
    public void start() {
        moveLeft = new VacuumMoveLeft(this);
        moveRight = new VacuumMoveRight(this);
        clean = new VacuumClean(this);
        stop = new VacuumStop(this);

        cleanIfNeededOrStopIfAllRoomsAreClean();

        if (!silent) {
            System.out.println("All rooms are clean\n");
            printCommandHistory();
        }
    }

    private void cleanIfNeededOrStopIfAllRoomsAreClean() {
        while (notDone()) {
            if (!datastore.getWorld().isRoomClean(datastore.getRoomNumber())) {
                clean.execute();
            } else {
                moveToOtherRoom();
            }
        }
    }

    boolean notDone() {
        return datastore.getDirection() != Direction.STOP || !datastore.getWorld().areAllRoomsClean();
    }

    private void moveToOtherRoom() {
        if (datastore.getDirection() == Direction.RIGHT && checkRight()) {
            moveRight.execute();
        } else if (checkLeft()) {
            moveLeft.execute();
        } else {
            stop.execute();
        }
    }

    private boolean checkRight() {
        return datastore.getRoomNumber() + Direction.RIGHT.getVal() < datastore.getWorld().size();
    }

    private boolean checkLeft() {
        return datastore.getRoomNumber() + Direction.LEFT.getVal() >= 0;
    }

    @Override
    public void moveLeft() {
        datastore.setDirection(Direction.LEFT);
        datastore.addToCommandHistory(moveLeft);
        datastore.increaseRoomNumber(datastore.getDirection().getVal());
        datastore.decreasePoints(1);
        printDirection();
    }

    @Override
    public void moveRight() {
        datastore.setDirection(Direction.RIGHT);
        datastore.addToCommandHistory(moveRight);
        datastore.increaseRoomNumber(datastore.getDirection().getVal());
        datastore.decreasePoints(1);
        printDirection();
    }

    @Override
    public void clean() {
        datastore.getWorld().getRoom(datastore.getRoomNumber()).setClean(true);
        datastore.addToCommandHistory(clean);
        datastore.increasePoints(3);

        if (!silent)
            System.out.println("Cleaning: " + datastore.getRoomNumber());
    }

    @Override
    public void stop() {
        datastore.setDirection(Direction.STOP);
        datastore.addToCommandHistory(stop);
        printDirection();
    }

    private void printDirection() {
        if (!silent)
            System.out.printf("Moving: %d -> %d (%s)%n",
                    datastore.getRoomNumber() - datastore.getDirection().getVal(), datastore.getRoomNumber(),
                    datastore.getDirection());
    }

    private void printCommandHistory() {
        for (VacuumCommand command : datastore.getCommandHistory()) {
            System.out.println(command);
        }
    }
}