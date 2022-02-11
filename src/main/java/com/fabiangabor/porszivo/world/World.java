package com.fabiangabor.porszivo.world;

import com.fabiangabor.porszivo.Room;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private final List<Room> rooms;

    public World() {
        rooms = new ArrayList<>();
    }

    public Room getRoom(int i) {
        return rooms.get(i);
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public int getSize() {
        return rooms.size();
    }

    public boolean areAllRoomsClean() {
        for (Room room : rooms) {
            if (!room.isClean()) {
                return false;
            }
        }
        return true;
    }

    public boolean isRoomClean(int i) {
        return getRoom(i).isClean();
    }

    void initWorld(int n) {
        Random rand;
        try {
            rand = SecureRandom.getInstanceStrong();
            for (int i = 0; i < n; i++) {
                Room room = new Room(rand.nextBoolean());
                addRoom(room);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Room room : rooms) {
            sb.append(room);
        }
        sb.append(String.format("%n"));
        return sb.toString();
    }
}
