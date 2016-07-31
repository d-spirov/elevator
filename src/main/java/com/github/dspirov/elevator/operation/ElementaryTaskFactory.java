package com.github.dspirov.elevator.operation;

/**
 * Factory for the elementary tasks for the elevator.
 *
 * Created by dspirov on 31/07/16.
 */
public class ElementaryTaskFactory {

    public static ElementaryTask createOneFloorUpTask() {
        return new OneFloorUp();
    }

    public static ElementaryTask crateOneFloorDownTask() {
        return new OneFloorDown();
    }

    public static ElementaryTask createOpenDoorsTask() {
        return new OpenDoors();
    }

    public static ElementaryTask createCloseDoorsTask() {
        return new CloseDoors();
    }

    public static ElementaryTask createWaitTask() {
        return new Wait();
    }

}
