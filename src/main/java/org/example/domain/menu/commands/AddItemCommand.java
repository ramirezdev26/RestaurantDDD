package org.example.domain.menu.commands;

import org.example.generic.Command;

public class AddItemCommand extends Command {

    private String itemId;
    private String category;
    private String description;
    private String name;
    private Integer price;
    private String menuId;

    public AddItemCommand(String itemId, String category, String description, String name, Integer price, String menuId) {
        this.itemId = itemId;
        this.category = category;
        this.description = description;
        this.name = name;
        this.price = price;
        this.menuId = menuId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getMenuId() {
        return menuId;
    }
}
