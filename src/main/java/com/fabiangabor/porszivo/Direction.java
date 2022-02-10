package com.fabiangabor.porszivo;

public enum Direction {
    LEFT(-1),
    RIGHT(1);

    private int val;

    Direction(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}