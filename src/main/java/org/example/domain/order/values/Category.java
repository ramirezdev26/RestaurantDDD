package org.example.domain.order.values;

import org.example.generic.ValueObject;

import java.util.Objects;

public class Category implements ValueObject<String> {

    private String category;

    public Category(String category) {
        if (!Objects.equals(category, "")) {
            this.category = category;
        } else throw new IllegalArgumentException("The category must not be an empty string");
    }
    @Override
    public String value() {
        return category;
    }
}
