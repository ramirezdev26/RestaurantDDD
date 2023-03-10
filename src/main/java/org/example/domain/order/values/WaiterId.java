package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.Identity;

import java.util.Objects;

public class WaiterId extends Identity {

    private WaiterId(String value) {
        super(value);
    }

    public WaiterId() {}

    public static WaiterId of(String value) {
        if (!Objects.equals(value, "")) {
            return new WaiterId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
