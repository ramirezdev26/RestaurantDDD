package org.example.domain.menu.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.Identity;

import java.util.Objects;

public class MenuId extends Identity {

    private MenuId(String value) {
        super(value);
    }

    public MenuId() {}

    public static MenuId of(String value) {
        if (!Objects.equals(value, "")) {
            return new MenuId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
