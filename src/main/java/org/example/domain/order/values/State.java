package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.ValueObject;

import java.util.Objects;

public class State implements ValueObject<String> {

    private String state;

    public State(String state) {
        if (!Objects.equals(state, "")) {
            this.state = state;
        } else throw new IllegalArgumentException("The state must not be an empty string");
    }
    @Override
    public String value() {
        return state;
    }
}
