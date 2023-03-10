package org.example.domain.order.commands;

import org.example.generic.Command;

public class CreateOrderCommand extends Command {

    private String orderId;
    private String date;
    private String clientId;

    public CreateOrderCommand(String orderId, String date, String clientId) {
        this.orderId = orderId;
        this.date = date;
        this.clientId = clientId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getDate() {
        return date;
    }

    public String getClientId() {
        return clientId;
    }
}
