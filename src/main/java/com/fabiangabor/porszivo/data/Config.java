package com.fabiangabor.porszivo.data;

public class Config {
    private final boolean silent;
    private final int worldsCount;
    private final int roomCount;

    public Config(boolean silent, int worldsCount, int roomCount) {
        this.silent = silent;
        this.worldsCount = worldsCount;
        this.roomCount = roomCount;
    }

    public boolean isSilent() {
        return silent;
    }

    public int getWorldsCount() {
        return worldsCount;
    }

    public int getRoomCount() {
        return roomCount;
    }
}
