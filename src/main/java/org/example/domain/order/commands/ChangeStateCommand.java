package org.example.domain.order.commands;

import org.example.generic.Command;

public class ChangeStateCommand extends Command {

    private String status;
    private String orderId;

    public ChangeStateCommand(String status, String orderId) {
        this.status = status;
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderId() {
        return orderId;
    }
}
