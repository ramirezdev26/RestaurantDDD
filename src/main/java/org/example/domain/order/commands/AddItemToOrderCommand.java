package org.example.domain.order.commands;

import org.example.generic.Command;

public class AddItemToOrderCommand extends Command {

    private String itemId;
    private String category;
    private String itemName;
    private Integer price;
    private Integer quantity;
    private String orderId;

    public AddItemToOrderCommand(String itemId, String category, String itemName, Integer price, Integer quantity, String orderId) {
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getOrderId() {
        return orderId;
    }
}
