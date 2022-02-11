package com.fabiangabor.porszivo.service;

import com.fabiangabor.porszivo.domain.Direction;
import com.fabiangabor.porszivo.domain.World;
import com.fabiangabor.porszivo.commands.*;
import com.fabiangabor.porszivo.data.Datastore;

public class VacuumController {
    private final Datastore datastore;

    private final VacuumCommand moveLeft;
    private final VacuumCommand moveRight;
    private final VacuumCommand stop;
    private final VacuumCommand clean;

    public VacuumController(World world, VacuumReceiver vacuum) {
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

    public Datastore getDatastore() {
        return datastore;
    }

    public void start() {
        datastore.addToDirectionHistory("START");
        cleanIfNeededOrStopIfAllRoomsAreClean();
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

    public int getPoints() {
        return datastore.getPoints();
    }
}