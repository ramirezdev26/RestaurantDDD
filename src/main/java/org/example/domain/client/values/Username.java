package org.example.domain.client.values;

import org.example.generic.ValueObject;

public class Username implements ValueObject<String> {

    private String username;

    public Username(String name) {
        this.username = username;
    }
    @Override
    public String value() {
        return username;
    }
}
