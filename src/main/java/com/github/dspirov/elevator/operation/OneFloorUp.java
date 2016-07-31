package com.github.dspirov.elevator.operation;

/**
 * Created by dspirov on 31/07/16.
 */
class OneFloorUp extends AbstractTask {

    @Override
    public void execute(OperativeElevator elevator) {
        super.execute(elevator);
        elevator.moveOneFloorUp();
    }

    @Override
    public String toString() {
        return " moving one floor up";
    }

}
