package com.fabiangabor.porszivo.data;

import com.fabiangabor.porszivo.commands.VacuumCommand;
import com.fabiangabor.porszivo.domain.Direction;
import com.fabiangabor.porszivo.domain.World;

import java.util.List;

public interface Datastore {
    World getWorld();

    Direction getDirection();

    void setDirection(Direction direction);

    int getRoomNumber();

    void setRoomNumber(int roomNumber);

    void increaseRoomNumber(int roomNumber);

    int getPoints();

    void increasePoints(int amount);

    void decreasePoints(int amount);

    List<VacuumCommand> getCommandHistory();

    void addToCommandHistory(VacuumCommand command);

    List<String> getDirectionHistory();

    void addToDirectionHistory(String command);

    void addToDirectionHistory(VacuumCommand command);

    boolean allRoomsAreClear();

    void setRoomClean();
}
