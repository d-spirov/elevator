package com.github.dspirov.elevator;

import com.github.dspirov.elevator.model.Elevator;
import com.github.dspirov.elevator.model.SimpleElevator;
import com.github.dspirov.elevator.operation.OperativeElevator;

/**
 * Created by dspirov on 31/07/16.
 */
class SimpleElevatorService implements ElevatorService{

    private SimpleElevator elevator;
    private ElevatorThread mainThread;

    public SimpleElevatorService() {
        this.elevator = new SimpleElevator();
    }

    public void init() {
        mainThread = new ElevatorThread(elevator);
        mainThread.start();
    }

    public void stop() {
        mainThread.stopRunning();
    }

    @Override
    public Elevator getElevator() {
        return elevator;
    }

    @Override
    public OperativeElevator getOperativeElevator() {
        return elevator;
    }

    public static void main(String[] args) {
        ElevatorService elevatorService = new SimpleElevatorService();
        elevatorService.init();
    }
}
