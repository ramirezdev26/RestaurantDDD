package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.ValueObject;

import java.util.Objects;

public class Password implements ValueObject<String> {

    private String password;

    public Password(String password) {
        if (!Objects.equals(password, "")) {
            this.password = password;
        } else throw new IllegalArgumentException("The password must not be an empty string");
    }
    @Override
    public String value() {
        return password;
    }
}
