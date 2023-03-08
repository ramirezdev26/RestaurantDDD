package org.example.domain.menu.values;

import org.example.generic.Identity;

public class ItemId extends Identity {

    private ItemId(String value) {
        super(value);
    }

    public ItemId() {}

    public static ItemId of(String value) {
        return new ItemId(value);
    }

}
