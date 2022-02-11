package com.fabiangabor.porszivo.domain;

public enum Direction {
    LEFT(-1),
    RIGHT(1),
    STOP(0);

    private final int val;

    Direction(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
