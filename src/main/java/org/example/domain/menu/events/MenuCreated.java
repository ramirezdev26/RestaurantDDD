package org.example.domain.menu.events;

import org.example.generic.DomainEvent;

public class MenuCreated extends DomainEvent {

    protected String date;

    public MenuCreated(String date) {
        super("restaurant.menu.menuCreated");
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
