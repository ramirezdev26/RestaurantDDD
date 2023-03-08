package org.example.domain.menu.events;

import org.example.generic.DomainEvent;

public class DefaultPricesApplied extends DomainEvent {

    private String menuId;
    private String promoId;

    public DefaultPricesApplied(String menuId, String promoId) {
        super("restaurant.menu.DefaultPricesApplied");
        this.menuId = menuId;
        this.promoId = promoId;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getPromoId() {
        return promoId;
    }
}
