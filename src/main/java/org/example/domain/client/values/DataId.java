package org.example.domain.client.values;

import org.example.generic.Identity;

import java.util.Objects;

public class DataId extends Identity {

    private DataId(String value) {
        super(value);
    }

    public DataId() {}

    public static DataId of(String value) {
        if (!Objects.equals(value, "")) {
            return new DataId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
