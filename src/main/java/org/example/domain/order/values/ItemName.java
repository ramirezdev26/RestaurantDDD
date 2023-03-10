package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.ValueObject;

import java.util.Objects;

public class ItemName implements ValueObject<String> {

    private String itemName;

    public ItemName(String itemName) {
        if (!Objects.equals(itemName, "")) {
            this.itemName = itemName;
        } else throw new IllegalArgumentException("The itemName must not be an empty string");
    }
    @Override
    public String value() {
        return itemName;
    }
}
