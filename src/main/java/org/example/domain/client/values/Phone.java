package org.example.domain.client.values;

import org.example.generic.ValueObject;

public class Phone implements ValueObject<String> {

    private String phone;

    public Phone(String phone) {
        this.phone = phone;
    }
    @Override
    public String value() {
        return phone;
    }
}
