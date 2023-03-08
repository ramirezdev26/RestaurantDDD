package org.example.domain.menu.values;

import org.example.generic.ValueObject;

public class Price implements ValueObject<Integer> {

    private Integer price;

    public Price(Integer price) {
        this.price = price;
    }
    @Override
    public Integer value() {
        return price;
    }
}
