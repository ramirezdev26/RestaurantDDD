package org.example.domain.menu.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.Identity;

import java.util.Objects;

public class PromoId extends Identity {

    private PromoId(String value) {
        super(value);
    }

    public PromoId() {}

    public static PromoId of(String value) {
        if (!Objects.equals(value, "")) {
            return new PromoId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}

