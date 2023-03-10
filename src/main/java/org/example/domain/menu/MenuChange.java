package org.example.domain.menu;

import org.example.domain.menu.events.*;
import org.example.domain.menu.values.*;
import org.example.generic.EventChange;

import java.util.HashSet;
import java.util.Set;

public class MenuChange extends EventChange {

    public MenuChange(Menu menu) {

        apply((MenuCreated event) -> {
            menu.itemList =  new HashSet<>();
            menu.date = new Date(event.getDate());
        });

        apply(( ItemAdded event ) -> {
            Item newItem = new Item(ItemId.of(event.getItemId()), new Category(event.getCategory()), new Description(event.getDescription()), new Name(event.getName()), new Price(event.getPrice()));
            menu.itemList.add(newItem);
        });

        apply(( ItemRemoved event ) -> {
            menu.itemList.removeIf(item -> item.identity().value().equals(event.getItemId()));
        });

        apply(( PromoAdded event ) -> {
            Set<ItemId> itemsIdList = new HashSet<>();
            for (String id : event.getItemIdList()){
                itemsIdList.add(ItemId.of(id));
            }
            menu.promo = new Promo(PromoId.of(event.getPromoId()), new QuantityOff(event.getQuantityOff()), itemsIdList);
        });

        apply(( PromoRemoved event ) -> {
                menu.promo = new Promo (PromoId.of(event.getPromoId()), new QuantityOff(0), new HashSet<>());
        });

        apply( (PromoApplied event) -> {
            for ( Item item : menu.itemList ){
               if(event.getItemIdList().contains(item.identity().value())) {
                   item.setPrice(new Price(item.getPrice().value() * (100 - event.getQuantityOff()) / 100));
               }
            }
        });

        apply( (DefaultPricesApplied event) -> {
            Integer quantityOff = menu.promo.getQuantityOff().value();
            Set<ItemId> itemIdList = menu.promo.getItemsList();
            for (Item item : menu.itemList) {
                if (itemIdList.contains(item.identity())) {
                    item.setPrice(new Price(item.getPrice().value() * 100 / (100 - quantityOff)));
                }
            }
        });

    }
}