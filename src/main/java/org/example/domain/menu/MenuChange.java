package org.example.domain.menu;

import org.example.domain.menu.events.*;
import org.example.domain.menu.values.*;
import org.example.generic.EventChange;

import java.util.ArrayList;
import java.util.List;

public class MenuChange extends EventChange {

    public MenuChange(Menu menu) {

        apply((MenuCreated event) -> {
            menu.itemList =  new ArrayList<>();
            menu.date = new Date(event.getDate());
        });

        apply(( ItemAdded event ) -> {
            Item item = new Item(ItemId.of(event.getItemId()), new Category(event.getCategory()), new Description(event.getDescription()), new Name(event.getName()), new Price(event.getPrice()));
            menu.itemList.add(item);
        });

        apply((ItemRemoved event) -> {
            menu.itemList.removeIf(item -> item.identity().value().equals(event.getItemId()));
        });

        apply(( PromoAdded event ) -> {
            List<ItemId> itemsIdList = new ArrayList<>();
            for (String id : event.getItemIdList()){
                itemsIdList.add(ItemId.of(id));
            }
            menu.promo = new Promo(PromoId.of(event.getPromoId()), new QuantityOff(event.getQuantityOff()), itemsIdList);
        });

        apply((PromoRemoved event) -> {
                menu.promo = new Promo (PromoId.of(event.getPromoId()), new QuantityOff(0), new ArrayList<>());
        });

        apply( (PromoApplied event) -> {
            for ( Item item : menu.itemList ){
               if(event.getItemIdList().contains(item.identity().value())) {
                   item.setPrice(new Price(item.getPrice().value() * (100 - event.getQuantityOff()) / 100));
               }
            }
        });

        apply( (DefaultPricesApplied event) -> {
            if(menu.promo.identity().value().equals(event.getPromoId())) {
                Integer quantityOff = menu.promo.getQuantityOff().value();
                List<ItemId> itemIdList = menu.promo.getItemsList();
                for (Item item : menu.itemList) {
                    if (itemIdList.contains(item.identity())) {
                        item.setPrice(new Price(item.getPrice().value() * 100 / (100 - quantityOff)));
                    }
                }
            } throw new IllegalArgumentException("The id provided doesn't match with the current promoId");
        });

    }
}