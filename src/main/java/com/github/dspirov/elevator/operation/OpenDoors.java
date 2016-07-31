package com.github.dspirov.elevator.operation;

/**
 * Created by dspirov on 31/07/16.
 */
class OpenDoors extends AbstractTask {

    @Override
    public void execute(OperativeElevator elevator) {
        super.execute(elevator);
        elevator.openDoors();
    }

    @Override
    public String toString() {
        return "opening doors";
    }
}
