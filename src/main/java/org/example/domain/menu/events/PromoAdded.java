package org.example.domain.menu.events;

import org.example.generic.DomainEvent;

import java.util.List;

public class PromoAdded extends DomainEvent {

    private String promoId;
    private Integer quantityOff;
    private List<String> itemIdList;

    public PromoAdded(String promoId, Integer quantityOff, List<String> itemIdList) {
        super("restaurant.menu.promoCreated");
        this.promoId = promoId;
        this.quantityOff = quantityOff;
        this.itemIdList = itemIdList;
    }

    public String getPromoId() {
        return promoId;
    }

    public Integer getQuantityOff() {
        return quantityOff;
    }

    public List<String> getItemIdList() {
        return itemIdList;
    }
}
