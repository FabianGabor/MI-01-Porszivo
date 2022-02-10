package com.fabiangabor.porszivo.commands;

public interface VacuumReceiver {
    void start();
    void moveLeft();
    void moveRight();
    void clean();
    void stop();
    int getPoints();
}
