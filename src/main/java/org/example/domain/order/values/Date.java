package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.ValueObject;

import java.util.Objects;

public class Date implements ValueObject<String> {

    private String date;

    public Date(String date) {
        if (!Objects.equals(date, "")) {
            this.date = date;
        } else throw new IllegalArgumentException("The date must not be an empty string");
    }
    @Override
    public String value() {
        return date;
    }
}
