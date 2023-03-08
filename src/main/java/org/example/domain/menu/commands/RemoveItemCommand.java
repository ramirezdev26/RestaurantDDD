package org.example.domain.menu.commands;

import org.example.generic.Command;

public class RemoveItemCommand extends Command {

    private String itemId;
    private String menuId;

    public RemoveItemCommand(String itemId, String menuId) {
        this.itemId = itemId;
        this.menuId = menuId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getMenuId() {
        return menuId;
    }
}
