package org.example.domain.menu.events;

import org.example.generic.DomainEvent;

public class PromoRemoved extends DomainEvent {

    private String promoId;

    public PromoRemoved(String promoId) {
        super("restaurant.menu.PromoRemoved");
        this.promoId = promoId;
    }

    public String getPromoId() {
        return promoId;
    }
}
