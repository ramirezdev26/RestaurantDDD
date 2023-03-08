package org.example.domain.menu;

import org.example.domain.menu.values.*;
import org.example.generic.Entity;

public class Item extends Entity<ItemId> {

    private Category category;
    private Description description;
    private Name name;
    private Price price;

    public Item(ItemId id, Category category, Description description, Name name, Price price) {
        super(id);
        this.category = category;
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public Description getDescription() {
        return description;
    }

    public Name getName() {
        return name;
    }
    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
