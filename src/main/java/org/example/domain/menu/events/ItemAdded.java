package org.example.domain.menu.events;

import org.example.generic.DomainEvent;

public class ItemAdded extends DomainEvent {

    private String itemId;

    private String category;
    private String description;
    private String name;
    private Integer price;

    public ItemAdded(String itemId, String category, String description, String name, Integer price) {
        super("restaurant.menu.itemAdded");
        this.itemId = itemId;
        this.category = category;
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
