package org.example.domain.menu.values;

import org.example.generic.ValueObject;

public class QuantityOff implements ValueObject<Integer> {

    private Integer quantityOff;

    public QuantityOff(Integer quantityOff) {
        this.quantityOff = quantityOff;
    }
    @Override
    public Integer value() {
        return quantityOff;
    }
}
