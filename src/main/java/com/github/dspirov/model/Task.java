package com.github.dspirov.model;

/**
 * Created by dspirov on 29/07/16.
 */
class Task {

    int from;
    Integer to;
    Direction direction;

    public Task(int from, Integer to, Direction direction) {
        this.from = from;
        this.to = to;
        this.direction = direction;
    }

    public int getFrom() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public Direction getDirection() {
        return direction;
    }
}
