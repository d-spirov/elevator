package com.github.dspirov;

import com.github.dspirov.model.Direction;
import com.github.dspirov.model.Elevator;
import com.github.dspirov.model.SimpleElevator;

/**
 * Created by dspirov on 29/07/16.
 */
public class ElevatorService {

    void init() {
        Elevator elevator = new SimpleElevator();
        elevator.call(10, Direction.UP);
        new Thread(new ElevatorThread(elevator)).run();
    }

    public static void main(String[] args) {
        ElevatorService elevatorService = new ElevatorService();
        elevatorService.init();
    }

}
