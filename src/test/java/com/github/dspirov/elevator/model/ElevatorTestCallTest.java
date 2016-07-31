package com.github.dspirov.elevator.model;

import com.github.dspirov.elevator.ElevatorModule;
import com.github.dspirov.elevator.ElevatorService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.*;

/**
 * Tests the all functionality for the elevator. The tests are dependant to each other so this is why priority is used
 * as general rule.
 *
 * Created by dspirov on 31/07/16.
 */
@Guice(modules = ElevatorModule.class)
public class ElevatorTestCallTest {

    private static final long SIXTEEN_SECONDS = 16000;
    private static final long TWENTY_SECONDS = 20000;

    @Inject
    private ElevatorService elevatorService;

    @BeforeMethod
    public void setUp() throws Exception {
        elevatorService.init();
    }

    @AfterMethod
    public void tearUp() throws InterruptedException {
        elevatorService.stop();
        Thread.currentThread().sleep(2000);
    }

    @Test(priority = 1, expectedExceptions = IllegalArgumentException.class)
    public void testCall_overflow() throws Exception {
        elevatorService.getElevator().call(16, Direction.UP);
    }

    @Test(priority = 2, expectedExceptions = IllegalArgumentException.class)
    public void testCall_underflow() throws Exception {
        elevatorService.getElevator().call(-1, Direction.UP);
    }

    @Test(priority = 3)
    public void testCall_6thfloor() throws Exception {
        elevatorService.getElevator().call(6, Direction.UP);
        Thread.currentThread().sleep(SIXTEEN_SECONDS);
        Assert.assertEquals(elevatorService.getElevator().getCurrentFloor(), 6);
    }

    @Test(priority = 4)
    public void testCall_2thfloor() throws Exception {
        elevatorService.getElevator().call(2, Direction.UP);
        Thread.currentThread().sleep(SIXTEEN_SECONDS);
        Assert.assertEquals(elevatorService.getElevator().getCurrentFloor(), 2);
    }

    @Test(priority = 4)
    public void testCall_10_then_8thfloor() throws Exception {
        elevatorService.getElevator().call(10, Direction.UP);
        elevatorService.getElevator().call(8, Direction.UP);
        Thread.currentThread().sleep(TWENTY_SECONDS);
        Assert.assertEquals(elevatorService.getElevator().getCurrentFloor(), 8);
    }

}