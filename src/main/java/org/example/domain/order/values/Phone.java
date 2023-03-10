package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.ValueObject;

import java.util.Objects;

public class Phone implements ValueObject<String> {

    private String phone;

    public Phone(String phone) {
        if ( phone.length() >= 10 ) {
            this.phone = phone;
        } else throw new IllegalArgumentException("The phone must has at least 10 characters");
    }
    @Override
    public String value() {
        return phone;
    }
}
