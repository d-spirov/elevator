package com.github.dspirov.elevator.operation;

/**
 * Created by dspirov on 31/07/16.
 */
class CloseDoors extends AbstractTask {

    @Override
    public void execute(OperativeElevator elevator) {
        super.execute(elevator);
        elevator.closeDoors();
    }

    @Override
    public String toString() {
        return "closing doors";
    }
}
