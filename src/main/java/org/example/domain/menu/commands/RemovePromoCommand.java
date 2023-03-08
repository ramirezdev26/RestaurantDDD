package org.example.domain.menu.commands;

import org.example.generic.Command;

public class RemovePromoCommand extends Command {

    private String promoId;
    private String menuId;

    public RemovePromoCommand(String promoId, String menuId) {
        this.promoId = promoId;
        this.menuId = menuId;
    }

    public String getPromoId() {
        return promoId;
    }

    public String getMenuId() {
        return menuId;
    }
}
