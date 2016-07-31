package com.github.dspirov.elevator.model;

import com.github.dspirov.elevator.ElevatorModule;
import com.github.dspirov.elevator.ElevatorService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

/**
 * Tests elevator operations regarding operative interface.
 *
 * It is not perfect solution since the test depend on each other. This is due to the threading implementation of the
 * elevator service.
 *
 * Created by dspirov on 31/07/16.
 */
@Guice(modules = ElevatorModule.class)
public class OperativeElevatorBaseTest {

    private static final long TWO_SECONDS = 2000;

    @Inject
    private ElevatorService elevatorService;

    @BeforeMethod
    public void setUp() throws Exception {
        elevatorService.init();
    }

    @AfterMethod
    public void tearUp() throws InterruptedException {
        elevatorService.stop();
        Thread.currentThread().sleep(TWO_SECONDS);
    }

    @Test(priority = 1)
    public void testMoveOneFloorUp() throws Exception {
        elevatorService.getOperativeElevator().moveOneFloorUp();
        Thread.currentThread().sleep(TWO_SECONDS);
        Assert.assertEquals(elevatorService.getElevator().getCurrentFloor(), 1);
    }

    @Test(priority = 2)
    public void testMoveOneFloorDown() throws Exception {
        elevatorService.getOperativeElevator().moveOneFloorDown();
        Thread.currentThread().sleep(TWO_SECONDS);
        Assert.assertEquals(elevatorService.getElevator().getCurrentFloor(), 0);
    }

    @Test(priority = 3, expectedExceptions = IllegalArgumentException.class)
    public void testMoveOneFloorDown_exception() throws Exception {
        elevatorService.getOperativeElevator().moveOneFloorDown();
    }

    @Test(priority = 4)
    public void testOpenDoors() throws Exception {
        elevatorService.getOperativeElevator().openDoors();
    }

    @Test(priority = 5)
    public void testCloseDoors() throws Exception {
        elevatorService.getOperativeElevator().closeDoors();
    }
}