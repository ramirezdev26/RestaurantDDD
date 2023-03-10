package org.example.domain.order.events;

import org.example.generic.DomainEvent;

public class StateChanged extends DomainEvent {

    private String status;

    public StateChanged(String status) {
        super("restaurant.order.StatusChanged");
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
