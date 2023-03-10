package org.example.domain.order.values;

import org.example.generic.ValueObject;

public class Quantity implements ValueObject<Integer> {

    private Integer quantity;

    public Quantity(Integer quantity) {
        if ( quantity >= 1 ) {
            this.quantity = quantity;
        } else throw new IllegalArgumentException("The quantity must bigger than 0");
    }
    @Override
    public Integer value() {
        return quantity;
    }
}
