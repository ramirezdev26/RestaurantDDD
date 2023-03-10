package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.Identity;

import java.util.Objects;

public class OrderItemId extends Identity {

    private OrderItemId(String value) {
        super(value);
    }

    public OrderItemId() {}

    public static OrderItemId of(String value) {
        if (!Objects.equals(value, "")) {
            return new OrderItemId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
