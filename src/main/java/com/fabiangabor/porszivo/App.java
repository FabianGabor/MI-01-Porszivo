package com.fabiangabor.porszivo;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
       List<Room> rooms = initRooms();
       Vacuum vacuum = new Vacuum(rooms);
       vacuum.start(1);
    }
    
    private static List initRooms() {
        Room room1 = new Room(false);
        Room room2 = new Room(true);
        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);
        return rooms;
    }
}
