package com.github.dspirov.model;

/**
 * Elevator interface.
 *
 * Created by dspirov on 29/07/16.
 */
public interface Elevator {

    /**
     * External uer calls the elevator to fo to certain floor. Is called by pressing the button in front of the door.
     *
     * @param floor which floor to go
     * @param direction direction requested
     */
    void call(int floor, Direction direction);

    /**
     * Users inside the elevator press the button.
     *
     * @param floor which floor to go
     */
    void go(int floor);

    /**
     * Called in the case of emergency.
     */
    void emergencyStop();

    void operativeStep();


}
