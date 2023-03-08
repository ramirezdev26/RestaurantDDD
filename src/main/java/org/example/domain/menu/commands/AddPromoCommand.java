package org.example.domain.menu.commands;

import org.example.generic.Command;

import java.util.List;

public class AddPromoCommand extends Command {

    private String promoId;
    private Integer quantityOff;
    private List<String> itemIdList;
    private String menuId;

    public AddPromoCommand(String promoId, Integer quantityOff, List<String> itemIdList, String menuId) {
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

    public List<String> getItemIdList() {
        return itemIdList;
    }

    public String getMenuId() {
        return menuId;
    }
}
