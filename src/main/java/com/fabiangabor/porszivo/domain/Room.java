package com.fabiangabor.porszivo.domain;

public class Room {
    private boolean clean;

    public Room(boolean clean) {
        this.clean = clean;
    }

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    @Override
    public String toString() {
        return clean + " ";
    }
}
