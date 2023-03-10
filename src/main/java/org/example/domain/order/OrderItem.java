package org.example.domain.order;

import org.example.domain.order.values.*;
import org.example.generic.Entity;

public class OrderItem extends Entity<OrderItemId> {

    private Category category;
    private ItemName itemName;
    private Price price;
    private Quantity quantity;

    public OrderItem(OrderItemId id, Category category, ItemName itemName, Price price, Quantity quantity) {
        super(id);
        this.category = category;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }


    public ItemName getItemName() {
        return itemName;
    }
    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
