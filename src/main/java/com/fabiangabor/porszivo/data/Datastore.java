package com.fabiangabor.porszivo.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fabiangabor.porszivo.commands.VacuumCommand;
import com.fabiangabor.porszivo.domain.Direction;
import com.fabiangabor.porszivo.domain.World;


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
                roomNumber +1
        ));
    }

    public void addToDirectionHistory(VacuumCommand command) {
        if (command.toString().equals("CLEAN") || command.toString().equals("STOP")) {
            directionHistory.add(String.format("%s: %d",
                    command,
                    roomNumber +1
            ));
        } else {
            directionHistory.add(String.format("MOVE:  %d -> %d (%s)",
                    roomNumber +1,
                    roomNumber + direction.getVal() +1,
                    direction
            ));
        }
    }

    public boolean allRoomsAreClear() {
        return world.areAllRoomsClean();
    }
}
