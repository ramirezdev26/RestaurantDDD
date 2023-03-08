package org.example.domain.menu.events;

import org.example.generic.DomainEvent;

public class ItemRemoved extends DomainEvent {

    private String itemId;

    public ItemRemoved(String itemId) {
        super("restaurant.menu.ItemRemoved");
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
}
