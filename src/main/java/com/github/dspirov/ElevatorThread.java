package com.github.dspirov;

import com.github.dspirov.model.Elevator;

/**
 * Created by dspirov on 29/07/16.
 */
class ElevatorThread implements Runnable {

    private Elevator elevator;
    private boolean running = true;

    ElevatorThread(Elevator elevator) {
        this.elevator = elevator;
    }

    public void run() {
        while(running) {
            elevator.operativeStep();
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
