package org.example.domain.client.values;

import org.example.generic.ValueObject;

public class Name implements ValueObject<String> {

    private String name;

    public Name(String name) {
        this.name = name;
    }
    @Override
    public String value() {
        return name;
    }
}
