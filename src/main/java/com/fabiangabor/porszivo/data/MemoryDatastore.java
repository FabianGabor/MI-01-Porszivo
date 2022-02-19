package com.fabiangabor.porszivo.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fabiangabor.porszivo.commands.VacuumCommand;
import com.fabiangabor.porszivo.domain.Direction;
import com.fabiangabor.porszivo.domain.World;


public class MemoryDatastore implements Datastore {
    private final World world;
    private Direction direction;
    private int roomNumber;
    private final Points points;
    private final List<VacuumCommand> commandHistory;
    private final List<String> directionHistory;

    public MemoryDatastore(World world) {
        this.world = world;
        this.direction = Direction.RIGHT;
        this.roomNumber = 0;
        this.points = new Points();
        commandHistory = new ArrayList<>();
        directionHistory = new ArrayList<>();
    }

    public MemoryDatastore(World world, int roomNumber) {
        this(world);
        this.roomNumber = roomNumber;
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int getRoomNumber() {
        return roomNumber;
    }

    @Override
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public void increaseRoomNumber(int roomNumber) {
        this.roomNumber += roomNumber;
    }

    @Override
    public int getPoints() {
        return points.getAmount();
    }

    @Override
    public void increasePoints(int amount) {
        points.increasePoints(amount);
    }

    @Override
    public void decreasePoints(int amount) {
        points.decreasePoints(amount);
    }

    @Override
    public List<VacuumCommand> getCommandHistory() {
        return Collections.unmodifiableList(commandHistory);
    }

    @Override
    public void addToCommandHistory(VacuumCommand command) {
        commandHistory.add(command);
    }

    @Override
    public List<String> getDirectionHistory() {
        return Collections.unmodifiableList(directionHistory);
    }

    @Override
    public void addToDirectionHistory(String command) {
        directionHistory.add(String.format("%s: %d",
                command,
                roomNumber +1
        ));
    }

    @Override
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

    @Override
    public boolean allRoomsAreClear() {
        return world.areAllRoomsClean();
    }

    @Override
    public void setRoomClean() {
        this.world.getRoom(roomNumber).setClean(true);
    }
}
