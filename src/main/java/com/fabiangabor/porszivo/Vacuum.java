package com.fabiangabor.porszivo;

import java.util.List;

public class Vacuum {
    private int roomNumber;
    private final List<Room> rooms;
    private Direction direction = Direction.RIGHT;

    public Vacuum(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void start(int roomNumber) {
        this.roomNumber = roomNumber;

        while (!allRoomsClean()) {
            clean();
        }
        System.out.println("All rooms are clean");
    }

    private void clean() {
        if (rooms.get(roomNumber).isClean()) {
            goToNextRoom();
            cleanRoom();
        }
    }

    private void cleanRoom() {
        System.out.println("Cleaning room " + roomNumber);
        rooms.get(roomNumber).setClean(true);
    }

    private void goToNextRoom() {
        if (direction == Direction.RIGHT && checkInbound()) {
            direction = Direction.LEFT;
        } else {
            if (direction == Direction.LEFT && checkInbound()) {
                direction = Direction.RIGHT;
            }
        }
        System.out.println("Going " + direction.name());
        roomNumber += direction.getVal();
    }

    private boolean checkInbound() {
        return (roomNumber + direction.getVal() >= rooms.size() || roomNumber + direction.getVal() < 0);
    }

    private boolean allRoomsClean() {
        for (Room room : rooms) {
            if (!room.isClean()) {
                return false;
            }
        }
        return true;
    }
}