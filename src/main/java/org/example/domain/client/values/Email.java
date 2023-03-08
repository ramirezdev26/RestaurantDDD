package org.example.domain.client.values;

import org.example.generic.ValueObject;

public class Email implements ValueObject<String> {

    private String email;

    public Email(String email) {
        this.email = email;
    }
    @Override
    public String value() {
        return email;
    }
}
