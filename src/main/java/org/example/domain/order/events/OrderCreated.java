package org.example.domain.order.events;

import org.example.generic.DomainEvent;

public class OrderCreated extends DomainEvent {

    private String date;

    public OrderCreated(String date) {
        super("restaurant.order.OrderCreated");
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
