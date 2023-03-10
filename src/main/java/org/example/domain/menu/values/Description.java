package org.example.domain.menu.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.ValueObject;

import java.util.Objects;

public class Description implements ValueObject<String> {

    private String description;

    public Description(String description) {
        if (!Objects.equals(description, "")) {
            this.description = description;
        } else throw new IllegalArgumentException("The description must not be an empty string");
    }
    @Override
    public String value() {
        return description;
    }
}
