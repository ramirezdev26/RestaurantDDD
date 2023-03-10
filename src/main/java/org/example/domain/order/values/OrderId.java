package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.Identity;

import java.util.Objects;

public class OrderId extends Identity {

    private OrderId(String value) {
        super(value);
    }

    public OrderId() {}

    public static OrderId of(String value) {
        if (!Objects.equals(value, "")) {
            return new OrderId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
