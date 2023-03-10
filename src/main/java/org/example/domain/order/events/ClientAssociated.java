package org.example.domain.order.events;

import org.example.generic.DomainEvent;

public class ClientAssociated extends DomainEvent {

    private String clientId;
    private String orderId;

    public ClientAssociated(String clientId, String orderId) {
        super("restaurant.order.ClientAssociated");
        this.clientId = clientId;
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getOrderId() {
        return orderId;
    }
}
