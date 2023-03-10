package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.ValueObject;

import java.util.Objects;

public class Username implements ValueObject<String> {

    private String username;

    public Username(String username) {
        if (!Objects.equals(username, "")) {
            this.username = username;
        } else throw new IllegalArgumentException("The userName must not be an empty string");
    }
    @Override
    public String value() {
        return username;
    }
}
