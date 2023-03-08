package org.example.domain.menu.events;

import org.example.generic.DomainEvent;

import java.util.List;

public class PromoApplied extends DomainEvent {

    private String menuId;
    private Integer quantityOff;
    private List<String> itemIdList;

    public PromoApplied(String menuId, Integer quantityOff, List<String> itemIdList) {
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

    public List<String> getItemIdList() {
        return itemIdList;
    }
}
