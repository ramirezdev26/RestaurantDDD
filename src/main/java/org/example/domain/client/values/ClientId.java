package org.example.domain.client.values;

import org.example.generic.Identity;

import java.util.Objects;

public class ClientId extends Identity {

    private ClientId(String value) {
        super(value);
    }

    public ClientId() {}

    public static ClientId of(String value) {
        if (!Objects.equals(value, "")) {
            return new ClientId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
