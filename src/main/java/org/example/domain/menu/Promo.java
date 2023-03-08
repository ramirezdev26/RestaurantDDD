package org.example.domain.menu;

import org.example.domain.menu.values.ItemId;
import org.example.domain.menu.values.PromoId;
import org.example.domain.menu.values.QuantityOff;
import org.example.generic.Entity;

import java.util.List;

public class Promo extends Entity<PromoId> {

    private QuantityOff quantityOff;
    private List<ItemId> itemsList; // set

    public Promo(PromoId id, QuantityOff quantityOff, List<ItemId> itemsList) {
        super(id);
        this.quantityOff = quantityOff;
        this.itemsList = itemsList;
    }

    public QuantityOff getQuantityOff() {
        return quantityOff;
    }

    public List<ItemId> getItemsList() {
        return itemsList;
    }
}
