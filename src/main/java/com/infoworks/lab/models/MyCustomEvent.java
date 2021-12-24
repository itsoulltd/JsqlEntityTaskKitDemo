package com.infoworks.lab.models;

import com.infoworks.lab.rest.models.events.Event;

public class MyCustomEvent extends Event {
    private Passenger passenger;

    public Passenger getPassenger() {
        return passenger;
    }

    public MyCustomEvent setPassenger(Passenger passenger) {
        this.passenger = passenger;
        return this;
    }
}
