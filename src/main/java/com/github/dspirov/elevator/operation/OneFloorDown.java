package com.github.dspirov.elevator.operation;

/**
 * Created by dspirov on 31/07/16.
 */
class OneFloorDown extends AbstractTask {

    @Override
    public String toString() {
        return " moving one floor down";
    }

    @Override
    public void execute(OperativeElevator elevator) {
        super.execute(elevator);
        elevator.moveOneFloorDown();
    }
}
