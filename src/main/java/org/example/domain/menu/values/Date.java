package org.example.domain.menu.values;

import org.example.generic.ValueObject;

public class Date implements ValueObject<String> {

    private String date;

    public Date(String date) {
        this.date = date;
    }
    @Override
    public String value() {
        return date;
    }
}
