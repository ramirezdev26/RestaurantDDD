package org.example.domain.client.values;

import org.example.generic.Identity;

public class DataId extends Identity {

    private DataId(String value) {
        super(value);
    }

    public DataId() {}

    public static DataId of(String value) {
        return new DataId(value);
    }

}
