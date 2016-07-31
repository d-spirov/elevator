package com.github.dspirov.elevator;

import com.github.dspirov.elevator.model.Elevator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main thread that runs and executes steps of the elevator.
 *
 * Created by dspirov on 29/07/16.
 */
class ElevatorThread extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(ElevatorThread.class);

    public static final int ONE_SECOND = 1000;
    private Elevator elevator;
    private boolean running = true;

    ElevatorThread(Elevator elevator) {
        this.elevator = elevator;
    }

    public void run() {
        while(running) {
            elevator.operativeStep();
            try {
                Thread.currentThread().sleep(ONE_SECOND);
            } catch (InterruptedException e) {
               LOG.error("Error during main loop", e);
            }
        }
        LOG.info("Exiting...");
    }

    void stopRunning() {
        this.running = false;
    }
}
