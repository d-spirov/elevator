## Elevator

Write a program to operate an elevator in a building with 15 floors.
- should be able to take user requests
- open and close doors
- move upward and downward
- should not keep any user to wait for undefinite time

## Content of the repository

The repository contains implementation for the given task and unit tests for basic functionalities.

### Implementation

Elevator is implemented as a task execution engine with some defined states and tasks. Initial idea is that should be as a state-machine, which is not totally wrong even in the current solution.

The idea is that elevator itself keeps the state - current floor, doors opened etc. When user gives request to the elevator (either by pressing the button near the door or the buttons inside) this should trigger the elevator to do some action.

The whole elevator service is running as a backgound thread and is always executing the main loop of the elevator.

User requests are always put to the Queue. When the main loop is executed is checked if there is something in the queue. If there is task to be fetched is taken and then action in the elevator is executed.

Elevator actions are as well operated by another Queue. Actions in the elevator queue are inserted based on the user requests which is being processed. These actions are elementary actions - like a step above, step down, open doors etc.

In the current implementation there is a sleep of 1 sec in the task execution - in order to make some timeout so it would be more realistic when the elevator gets connected to some UI for example.


### Tests
- Testing is not 'standard' since the implementation of the elevator service is done by the background threads and step-by-step execution approach
- The current sleep time between executing steps is set to quite high - 1 second. The purpose is to be able to follow 
the events more realistic - for example if there is UI which can allow user to test the solution. 

Tests are configured in a separate testng.xml file because they need to be separated for the DI done by Guice. All tests are executed by the maven install goal - **mvn clean install**.