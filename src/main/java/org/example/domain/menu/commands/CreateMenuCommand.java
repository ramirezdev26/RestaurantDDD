package org.example.domain.menu.commands;

import org.example.generic.Command;

public class CreateMenuCommand extends Command {

    String menuId;
    String date;

    public CreateMenuCommand(String menuId, String date) {
        this.menuId = menuId;
        this.date = date;
    }

    public String getMenuId() {
        return menuId;
    }

    public String getDate() {
        return date;
    }
}
