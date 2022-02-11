package com.fabiangabor.porszivo.service;

import com.fabiangabor.porszivo.Direction;
import com.fabiangabor.porszivo.world.World;
import com.fabiangabor.porszivo.commands.*;
import com.fabiangabor.porszivo.datastore.Datastore;

public class VacuumController {
    private final VacuumReceiver vacuum;
    private final Datastore datastore;

    private final VacuumCommand moveLeft;
    private final VacuumCommand moveRight;
    private final VacuumCommand stop;
    private final VacuumCommand clean;

    public VacuumController(World world, VacuumReceiver vacuum) {
        this.vacuum = vacuum;

        this.datastore = new Datastore(world);
        this.datastore.setRoomNumber(0);

        moveLeft = new VacuumMoveLeft(vacuum, datastore);
        moveRight = new VacuumMoveRight(vacuum, datastore);
        clean = new VacuumClean(vacuum, datastore);
        stop = new VacuumStop(vacuum, datastore);
    }

    public VacuumController(World world, VacuumReceiver vacuum, int roomNumber) {
        this(world, vacuum);
        this.datastore.setRoomNumber(roomNumber);
    }

    public void start() {
        cleanIfNeededOrStopIfAllRoomsAreClean();

        if (!vacuum.isSilent()) {
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
        return datastore.getDirection() != Direction.STOP || !datastore.allRoomsAreClear();
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
        return datastore.getRoomNumber() + Direction.RIGHT.getVal() < datastore.getWorld().getSize();
    }

    private boolean checkLeft() {
        return datastore.getRoomNumber() + Direction.LEFT.getVal() >= 0;
    }

    private void printDirection() {
        if (!vacuum.isSilent())
            System.out.printf("Moving: %d -> %d (%s)%n",
                    datastore.getRoomNumber() - datastore.getDirection().getVal(),
                    datastore.getRoomNumber(),
                    datastore.getDirection());
    }

    private void printCommandHistory() {
        for (VacuumCommand command : datastore.getCommandHistory()) {
            System.out.println(command);
        }
    }

    public int getPoints() {
        return datastore.getPoints();
    }
}
