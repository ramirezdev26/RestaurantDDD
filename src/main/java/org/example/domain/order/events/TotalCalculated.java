package org.example.domain.order.events;

import org.example.generic.DomainEvent;

public class TotalCalculated extends DomainEvent {

    private String orderId;

    public TotalCalculated(String orderId) {
        super("restaurant.order.TotalCalculated");
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
