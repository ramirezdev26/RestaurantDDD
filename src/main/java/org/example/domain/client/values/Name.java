package org.example.domain.client.values;

import org.example.generic.ValueObject;

import java.util.Objects;

public class Name implements ValueObject<String> {

    private String name;

    public Name(String name) {
        if (!Objects.equals(name, "")) {
            this.name = name;
        } else throw new IllegalArgumentException("The name must not be an empty string");
    }
    @Override
    public String value() {
        return name;
    }
}
