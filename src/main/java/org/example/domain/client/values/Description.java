package org.example.domain.client.values;

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
