package com.fabiangabor.porszivo.commands;

import com.fabiangabor.porszivo.data.Datastore;
import com.fabiangabor.porszivo.domain.Direction;

public interface VacuumReceiver {
    void moveLeft(VacuumCommand command, Datastore datastore, Direction direction);
    void moveRight(VacuumCommand command, Datastore datastore, Direction direction);
    void clean(VacuumCommand command, Datastore datastore);
    void stop(VacuumCommand command, Datastore datastore, Direction direction);
    boolean isSilent();
}
