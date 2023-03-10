package org.example.domain.order.commands;

import org.example.generic.Command;

public class RemoveItemFromOrderCommand extends Command {

    private String itemId;
    private String orderId;

    public RemoveItemFromOrderCommand(String itemId, String orderId) {
        this.itemId = itemId;
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getOrderId() {
        return orderId;
    }
}
