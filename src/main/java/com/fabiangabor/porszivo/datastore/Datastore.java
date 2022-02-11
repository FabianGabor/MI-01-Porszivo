package com.fabiangabor.porszivo.datastore;

import com.fabiangabor.porszivo.Direction;
import com.fabiangabor.porszivo.world.World;
import com.fabiangabor.porszivo.commands.VacuumCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datastore {
    private final World world;
    private Direction direction;
    private int roomNumber;
    private int points;
    private final List<VacuumCommand> commandHistory;

    public Datastore(World world) {
        this.world = world;
        this.direction = Direction.RIGHT;
        this.points = 0;
        commandHistory = new ArrayList<>();
    }

    public Datastore(World world, int roomNumber) {
        this.world = world;
        this.direction = Direction.RIGHT;
        this.roomNumber = roomNumber;
        commandHistory = new ArrayList<>();
    }

    public World getWorld() {
        return world;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void increaseRoomNumber(int roomNumber) {
        this.roomNumber += roomNumber;
    }

    public int getPoints() {
        return points;
    }

    public void increasePoints(int amount) {
        points += amount;
    }

    public void decreasePoints(int amount) {
        points -= amount;
    }

    public List<VacuumCommand> getCommandHistory() {
        return Collections.unmodifiableList(commandHistory);
    }

    public void addToCommandHistory(VacuumCommand command) {
        commandHistory.add(command);
    }

    public boolean allRoomsAreClear() {
        return world.areAllRoomsClean();
    }
}