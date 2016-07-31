package com.github.dspirov.elevator;

import com.github.dspirov.elevator.model.Elevator;
import com.github.dspirov.elevator.operation.OperativeElevator;

/**
 * Service that starts the elevator/ main thread.
 *
 * Created by dspirov on 29/07/16.
 */
public interface ElevatorService {

    void init();

    void stop();

    Elevator getElevator();

    OperativeElevator getOperativeElevator();

}
