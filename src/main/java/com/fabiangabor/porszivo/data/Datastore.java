package com.fabiangabor.porszivo.data;

import com.fabiangabor.porszivo.domain.Direction;
import com.fabiangabor.porszivo.domain.World;
import com.fabiangabor.porszivo.commands.VacuumCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Datastore {
    private final World world;
    private Direction direction;
    private int roomNumber;
    private final Points points;
    private final List<VacuumCommand> commandHistory;
    private final List<String> directionHistory;

    public Datastore(World world) {
        this.world = world;
        this.direction = Direction.RIGHT;
        this.roomNumber = 0;
        this.points = new Points();
        commandHistory = new ArrayList<>();
        directionHistory = new ArrayList<>();
    }

    public Datastore(World world, int roomNumber) {
        this(world);
        this.roomNumber = roomNumber;
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
        return points.getAmount();
    }

    public void increasePoints(int amount) {
        points.increasePoints(amount);
    }

    public void decreasePoints(int amount) {
        points.decreasePoints(amount);
    }

    public List<VacuumCommand> getCommandHistory() {
        return Collections.unmodifiableList(commandHistory);
    }

    public void addToCommandHistory(VacuumCommand command) {
        commandHistory.add(command);
    }

    public List<String> getDirectionHistory() {
        return Collections.unmodifiableList(directionHistory);
    }

    public void addToDirectionHistory(String command) {
        directionHistory.add(String.format("%s: %d",
                command,
                roomNumber
        ));
    }

    public void addToDirectionHistory(VacuumCommand command) {
        if (command.toString().equals("CLEAN") || command.toString().equals("STOP")) {
            directionHistory.add(String.format("%s: %d",
                    command,
                    roomNumber
            ));
        } else {
            directionHistory.add(String.format("MOVE:  %d -> %d (%s)",
                    roomNumber,
                    roomNumber + direction.getVal(),
                    direction
            ));
        }
    }

    public boolean allRoomsAreClear() {
        return world.areAllRoomsClean();
    }
}