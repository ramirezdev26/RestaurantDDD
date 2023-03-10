package org.example.domain.menu.events;

import org.example.generic.DomainEvent;

import java.util.List;
import java.util.Set;

public class PromoApplied extends DomainEvent {

    private String menuId;
    private Integer quantityOff;
    private Set<String> itemIdList;

    public PromoApplied(String menuId, Integer quantityOff, Set<String> itemIdList) {
        super("restaurant.menu.PromoApplied");
        this.menuId = menuId;
        this.quantityOff = quantityOff;
        this.itemIdList = itemIdList;
    }

    public String getMenuId() {
        return menuId;
    }

    public Integer getQuantityOff() {
        return quantityOff;
    }

    public Set<String> getItemIdList() {
        return itemIdList;
    }
}
