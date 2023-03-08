package org.example.domain.client.values;

import org.example.generic.ValueObject;

public class Password implements ValueObject<String> {

    private String password;

    public Password(String name) {
        this.password = password;
    }
    @Override
    public String value() {
        return password;
    }
}
