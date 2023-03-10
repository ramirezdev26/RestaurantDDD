package org.example.domain.menu;

import org.example.domain.menu.events.*;
import org.example.domain.menu.values.Date;
import org.example.domain.menu.values.ItemId;
import org.example.domain.menu.values.MenuId;
import org.example.domain.menu.values.PromoId;
import org.example.generic.AggregateRoot;
import org.example.generic.DomainEvent;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Menu extends AggregateRoot<MenuId> {

    protected Set<Item> itemList; // set

    protected Promo promo;
    protected Date date;

    public Menu(MenuId id, Date date) {
        super(id);
        subscribe(new MenuChange(this));
        appendChange(new MenuCreated(date.value()));
    }

    private Menu(MenuId id) {
        super(id);
        subscribe(new MenuChange(this));
    }

    public static Menu from(MenuId menuId, List<DomainEvent> events) {
        var menu = new Menu(menuId);
        events.forEach(menu::applyEvent);
        return menu;
    }

    public void addToListOfItems(ItemId id, String category, String description, String name, Integer price){
        Objects.requireNonNull(id);
        Objects.requireNonNull(category);
        Objects.requireNonNull(description);
        Objects.requireNonNull(name);
        Objects.requireNonNull(price);
        appendChange(new ItemAdded(id.value(), category, description, name, price)).apply();
    }

    public void removeFromListOfItems(String itemId){
        Objects.requireNonNull(itemId);
        appendChange(new ItemRemoved(itemId)).apply();
    }

    public void addAPromo(PromoId id, Integer quantityOff, Set<String> itemIdList){
        Objects.requireNonNull(id);
        Objects.requireNonNull(quantityOff);
        Objects.requireNonNull(itemIdList);
        appendChange(new PromoAdded(id.value(), quantityOff, itemIdList)).apply();
    }

    public void removePromo(String promoId){
        Objects.requireNonNull(promoId);
        appendChange(new PromoRemoved(promoId)).apply();
    }

    public void applyPromo(String menuId, Integer quantityOff, Set<String> itemIdList){
        Objects.requireNonNull(quantityOff);
        Objects.requireNonNull(itemIdList);
        appendChange(new PromoApplied(menuId, quantityOff, itemIdList)).apply();
    }

    public void applyDefaultPrices(String menuId, String promoId){
        Objects.requireNonNull(menuId);
        Objects.requireNonNull(promoId);
        appendChange(new DefaultPricesApplied(menuId, promoId)).apply();
    }

    public Set<Item> getItemList() {
        return itemList;
    }

    public Promo getPromo() {
        return promo;
    }

    public Date getDate() {
        return date;
    }
}
