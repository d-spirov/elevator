package com.github.dspirov.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dspirov on 29/07/16.
 */
public class SimpleElevator implements Elevator {

    private List<Task> tasks;
    private int currentLocation;
    private ElevatorState elevatorState;
    private OperativeTask currentTask;

    public SimpleElevator() {
        this.elevatorState = ElevatorState.IDLE;
        this.currentLocation = 0;
        this.tasks = new ArrayList<Task>();
    }

    public synchronized void call(int floor, Direction direction) {
        System.out.println("Got called from the " + floor + " floor " + direction);
        tasks.add(new Task(floor, null, direction));
    }

    public void go(int floor) {
        System.out.println("Got command to go to the " + floor + " floor");
        setCurrentTask(new OperativeTask(floor));
    }

    void setCurrentTask(OperativeTask t) {
        this.currentTask = t;
    }

    public void emergencyStop() {
        elevatorState = ElevatorState.BROKEN;
    }

    public void operativeStep() {
        if(this.elevatorState == ElevatorState.IDLE) {
            if(tasks.isEmpty()) {
                System.out.println("I'm idle, nothing to do on the " + currentLocation + "floor ...");
            } else {
                Task t = getNextTask();
                this.currentTask = new OperativeTask(t.getFrom());
                move();
            }
        } else {
            move();
        }
    }

    private void move() {
        if(currentTask == null) {
            return;
        }
        System.out.println("Moving ");
        if(currentTask.getTo() == currentLocation) {
            this.currentTask = null;
            this.elevatorState = ElevatorState.DOORS_OPEN;
        } else {
            if(this.currentLocation < currentTask.getTo()) {
                this.currentLocation++;
                this.elevatorState = ElevatorState.GOING_UP;
            } else {
                this.currentLocation--;
                this.elevatorState = ElevatorState.GOING_DOWN;
            }
        }
    }

    private Task getNextTask() {
        Task t = tasks.get(0);
        tasks = tasks.subList(1, tasks.size());
        return t;
    }
}
