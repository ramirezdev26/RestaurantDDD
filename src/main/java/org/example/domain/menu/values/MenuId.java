package org.example.domain.menu.values;

import org.example.generic.Identity;

public class MenuId extends Identity {

    private MenuId(String value) {
        super(value);
    }

    public MenuId() {}

    public static MenuId of(String value) {
        return new MenuId(value);
    }

}
