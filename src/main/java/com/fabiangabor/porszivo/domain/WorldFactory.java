package com.fabiangabor.porszivo.domain;

public class WorldFactory implements Factory{
    @Override
    public World create(int size) {
        World world = new World();
        world.initWorld(size);
        return world;
    }
}
