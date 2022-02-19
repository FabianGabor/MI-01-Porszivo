package com.fabiangabor.porszivo.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WorldFactory implements Factory{

    @Override
    public World create(int size) {
        World world = new World();
        world.initWorld(size);
        return world;
    }
}
