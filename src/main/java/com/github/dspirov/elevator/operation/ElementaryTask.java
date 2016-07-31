package com.github.dspirov.elevator.operation;

/**
 * The smallest task for the elevator. This defines elementary operations such as open-doors, move one floor up, move one
 * floor down etc.
 *
 * Created by dspirov on 29/07/16.
 */
public interface ElementaryTask {

    void execute(OperativeElevator elevator);

}
