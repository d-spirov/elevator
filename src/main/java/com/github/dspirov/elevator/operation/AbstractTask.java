package com.github.dspirov.elevator.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Smallest task which is acceptable for the elevator.
 *
 * Created by dspirov on 29/07/16.
 */
abstract class AbstractTask implements ElementaryTask {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public void execute(OperativeElevator elevator) {
        LOG.info("Executing the task " + this);
    }

}
