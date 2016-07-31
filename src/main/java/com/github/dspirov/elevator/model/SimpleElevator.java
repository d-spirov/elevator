package com.github.dspirov.elevator.model;

import com.github.dspirov.elevator.operation.ElementaryTask;
import com.github.dspirov.elevator.operation.ElementaryTaskFactory;
import com.github.dspirov.elevator.operation.OperativeElevator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Simple implementation for the elevator.
 *
 * Created by dspirov on 29/07/16.
 */
public class SimpleElevator implements Elevator , OperativeElevator {

    public static final int MAX_FLOORS = 15;

    private static final Logger LOG = LoggerFactory.getLogger(SimpleElevator.class);

    private Queue<UserRequest> userRequests;
    private int currentLocation;
    private ElevatorState elevatorState;
    private Queue<ElementaryTask> executiveTasks;

    public SimpleElevator() {
        this.elevatorState = ElevatorState.IDLE;
        this.currentLocation = 0;
        this.userRequests = new LinkedList<>();
        this.executiveTasks = new LinkedList<>();
    }

    @Override
    public int getCurrentFloor() {
        return currentLocation;
    }

    public synchronized void call(int floor, Direction direction) {
        validateFloor(floor);
        userRequests.add(new UserRequest(floor, direction));
        LOG.info("Got called from the " + floor + " floor " + direction);
    }

    public void go(int floor) {
        validateFloor(floor);
        createTasksToGoToFloor(floor);
        LOG.info("Got command to go to the " + floor + " floor");
    }


    public void emergencyStop() {
        elevatorState = ElevatorState.EMERGENCY_STOP;
        LOG.warn("Elevator got into the EMERGENCY STOP state");
    }

    public void operativeStep() {
        checkForNewTasks();
        executeElementaryTask();
    }

    @Override
    public void moveOneFloorUp() {
        if(currentLocation > MAX_FLOORS) {
            throw new IllegalStateException(String.format("Cannot go more then buildings top %d", MAX_FLOORS));
        }
        this.currentLocation++;
        LOG.info("Moved to new location {}", this.currentLocation);
    }

    @Override
    public void moveOneFloorDown() {
        if(currentLocation == 0) {
            throw new IllegalArgumentException("Cannot go to floors below 0");
        }
        this.currentLocation--;
        LOG.info("Moved to new location {}", this.currentLocation);
    }

    @Override
    public void closeDoors() {
        LOG.info("Closing elevator doors");
    }

    @Override
    public void openDoors() {
        LOG.info("Opening elevator doors");
    }


    private void checkForNewTasks() {
        if(this.executiveTasks.isEmpty()) {
            if(userRequests.isEmpty()) {
                LOG.info("I'm idle, nothing to do on the {} floor ...", this.currentLocation);
            } else {
                try {
                    UserRequest t = getNextRequest();
                    createTasksToGoToFloor(t.getTo());
                } catch (NoSuchElementException e) {
                    LOG.debug("Tasks queue is empty...");
                }
            }
        }
    }

    private void createTasksToGoToFloor(int floor) {
        if(floor < this.currentLocation) {
            for (int i = this.currentLocation; i > floor; i--) {
                executiveTasks.add(ElementaryTaskFactory.crateOneFloorDownTask());
            }
            addOpenCloseDoors();
        } else {
            for (int i = this.currentLocation; i < floor; i++) {
                executiveTasks.add(ElementaryTaskFactory.createOneFloorUpTask());
            }
            addOpenCloseDoors();
        }
    }

    private void addOpenCloseDoors() {
        executiveTasks.add(ElementaryTaskFactory.createOpenDoorsTask());
        executiveTasks.add(ElementaryTaskFactory.createWaitTask());
        executiveTasks.add(ElementaryTaskFactory.createCloseDoorsTask());
    }

    private void executeElementaryTask() {
        try {
            ElementaryTask task = executiveTasks.remove();
            task.execute(this);
        }catch (NoSuchElementException e) {
            LOG.debug("No elementary tasks found...");
        }
    }

    private void validateFloor(int floor) {
        if(floor < 1 || floor > 15) {
            throw new IllegalArgumentException(String.format("Invalid parameter for the floor, range is between %d and %d", 0, MAX_FLOORS));
        }
    }

    /*
    Gets the next request to serve.
     */
    private UserRequest getNextRequest() {
        UserRequest userRequest = userRequests.remove();
        return userRequest;
    }


}
