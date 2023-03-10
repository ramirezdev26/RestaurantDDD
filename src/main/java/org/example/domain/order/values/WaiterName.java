package org.example.domain.order.values;

import org.example.domain.client.values.AccountId;
import org.example.generic.ValueObject;

import java.util.Objects;

public class WaiterName implements ValueObject<String> {

    private String waiterName;

    public WaiterName(String waiterName) {
        if (!Objects.equals(waiterName, "")) {
            this.waiterName = waiterName;
        } else throw new IllegalArgumentException("The waiterName must not be an empty string");
    }
    @Override
    public String value() {
        return waiterName;
    }
}
