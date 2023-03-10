package org.example.domain.order.events;

import org.example.domain.order.values.*;
import org.example.generic.DomainEvent;

public class ItemAddedToOrder extends DomainEvent {

    private String itemId;
    private String category;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public String getItemId() {
        return itemId;
    }

    public ItemAddedToOrder(String itemId, String category, String itemName, Integer price, Integer quantity) {
        super("restaurant.order.ItemAddedToOrder");
        this.itemId = itemId;
        this.category = category;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
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
}
