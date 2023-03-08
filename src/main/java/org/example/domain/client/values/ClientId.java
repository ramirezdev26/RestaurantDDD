package org.example.domain.client.values;

import org.example.generic.Identity;

public class ClientId extends Identity {

    private ClientId(String value) {
        super(value);
    }

    public ClientId() {}

    public static ClientId of(String value) {
        return new ClientId(value);
    }

}
