package org.example.domain.menu.commands;

import org.example.generic.Command;

import java.util.List;
import java.util.Set;

public class AddPromoCommand extends Command {

    private String promoId;
    private Integer quantityOff;
    private Set<String> itemIdList;
    private String menuId;

    public AddPromoCommand(String promoId, Integer quantityOff, Set<String> itemIdList, String menuId) {
        this.promoId = promoId;
        this.quantityOff = quantityOff;
        this.itemIdList = itemIdList;
        this.menuId = menuId;
    }

    public String getPromoId() {
        return promoId;
    }

    public Integer getQuantityOff() {
        return quantityOff;
    }

    public Set<String> getItemIdList() {
        return itemIdList;
    }

    public String getMenuId() {
        return menuId;
    }
}
