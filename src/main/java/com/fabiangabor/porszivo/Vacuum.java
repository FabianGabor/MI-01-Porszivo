package com.fabiangabor.porszivo;

import com.fabiangabor.porszivo.commands.*;

import java.util.ArrayList;
import java.util.List;

public class Vacuum implements VacuumReceiver {
    private final List<VacuumCommand> commandHistory = new ArrayList<>();
    private final World world;
    private int roomNumber = 0;
    private Direction direction = Direction.RIGHT;
    private int points;
    private VacuumCommand moveLeft;
    private VacuumCommand moveRight;
    private VacuumCommand stop;
    private VacuumCommand clean;
    private final boolean silent;


    public Vacuum(World world) {
        this.world = world;
        this.silent = false;
    }

    public Vacuum(World world, int roomNumber) {
        this.world = world;
        this.roomNumber = roomNumber;
        this.silent = false;
    }

    public Vacuum(World world, int roomNumber, boolean silent) {
        this.world = world;
        this.roomNumber = roomNumber;
        this.silent = silent;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public void start() {
        moveLeft = new VacuumMoveLeft(this);
        moveRight = new VacuumMoveRight(this);
        clean = new VacuumClean(this);
        stop = new VacuumStop(this);

        while (direction != Direction.STOP || !world.areAllRoomsClean()) {
            if (!world.isRoomClean(roomNumber)) {
                clean.execute();
            } else {
                moveToOtherRoom();
            }
        }

        if (!silent) {
            System.out.println("All rooms are clean\n");
            printCommandHistory();
        }
    }

    private void moveToOtherRoom() {
        if (direction == Direction.RIGHT && checkRight()) {
            moveRight.execute();
        } else if (checkLeft()) {
            moveLeft.execute();
        } else {
            stop.execute();
        }
    }

    private boolean checkRight() {
        return roomNumber + Direction.RIGHT.getVal() < world.size();
    }

    private boolean checkLeft() {
        return roomNumber + Direction.LEFT.getVal() >= 0;
    }

    @Override
    public void moveLeft() {
        direction = Direction.LEFT;
        roomNumber += direction.getVal();
        commandHistory.add(moveLeft);
        points--;
        printDirection();
    }

    @Override
    public void moveRight() {
        direction = Direction.RIGHT;
        roomNumber += direction.getVal();
        commandHistory.add(moveRight);
        points--;
        printDirection();
    }

    @Override
    public void clean() {
        world.getRoom(roomNumber).setClean(true);
        commandHistory.add(clean);
        points += 3;

        if (!silent)
            System.out.println("Cleaning: " + roomNumber);
    }

    @Override
    public void stop() {
        direction = Direction.STOP;
        commandHistory.add(stop);
        printDirection();
    }

    private void printDirection() {
        if (!silent)
            System.out.printf("Moving: %d -> %d (%s)%n", roomNumber - direction.getVal(), roomNumber, direction);
    }

    private void printCommandHistory() {
        for (VacuumCommand command : commandHistory) {
            System.out.println(command);
        }
    }
}