package org.example.domain.menu.values;

import org.example.generic.Identity;

public class PromoId extends Identity {

    private PromoId(String value) {
        super(value);
    }

    public PromoId() {}

    public static PromoId of(String value) {
        return new PromoId(value);
    }

}

