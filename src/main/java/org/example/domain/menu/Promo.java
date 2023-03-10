package org.example.domain.menu;

import org.example.domain.menu.values.ItemId;
import org.example.domain.menu.values.PromoId;
import org.example.domain.menu.values.QuantityOff;
import org.example.generic.Entity;

import java.util.List;
import java.util.Set;

public class Promo extends Entity<PromoId> {

    private QuantityOff quantityOff;
    private Set<ItemId> itemsList;

    public Promo(PromoId id, QuantityOff quantityOff, Set<ItemId> itemsList) {
        super(id);
        this.quantityOff = quantityOff;
        this.itemsList = itemsList;
    }

    public QuantityOff getQuantityOff() {
        return quantityOff;
    }

    public Set<ItemId> getItemsList() {
        return itemsList;
    }
}
