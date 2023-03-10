package org.example.domain.order.events;

import org.example.generic.DomainEvent;

public class ItemRemovedFromOrder extends DomainEvent {

    private String itemId;

    public ItemRemovedFromOrder(String itemId) {
        super("restaurant.order.ItemRemovedFromOrder");
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
}
