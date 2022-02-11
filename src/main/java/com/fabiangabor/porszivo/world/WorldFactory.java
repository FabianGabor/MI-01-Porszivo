package com.fabiangabor.porszivo.world;

public class WorldFactory implements Factory{
    @Override
    public World create(int size) {
        World world = new World();
        world.initWorld(size);
        return world;
    }
}
