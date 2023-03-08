package org.example.domain.menu.values;

import org.example.generic.ValueObject;

public class Category implements ValueObject<String> {

    private String category;

    public Category(String category) {
        this.category = category;
    }
    @Override
    public String value() {
        return category;
    }
}
