package com.github.dspirov.elevator;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/**
 * Created by dspirov on 31/07/16.
 */
public class ElevatorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ElevatorService.class).to(SimpleElevatorService.class).in(Scopes.SINGLETON);
    }
}
