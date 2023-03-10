package org.example.domain.order.values;

import org.example.generic.ValueObject;

public class Total implements ValueObject<Integer> {

    private Integer total;

    public Total(Integer total) {
        this.total = total;
    }
    @Override
    public Integer value() {
        return total;
    }

}
