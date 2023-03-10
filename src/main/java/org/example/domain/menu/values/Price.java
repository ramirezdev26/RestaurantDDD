package org.example.domain.menu.values;

import org.example.generic.ValueObject;

public class Price implements ValueObject<Integer> {

    private Integer price;

    public Price(Integer price) {
        if ( price > 0 ) {
            this.price = price;
        } else throw new IllegalArgumentException("The price must be bigger than 0");
    }
    @Override
    public Integer value() {
        return price;
    }
}
