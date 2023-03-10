package org.example.domain.menu.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.Identity;

import java.util.Objects;

public class ItemId extends Identity {

    private ItemId(String value) {
        super(value);
    }

    public ItemId() {}

    public static ItemId of(String value) {
        if (!Objects.equals(value, "")) {
            return new ItemId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
