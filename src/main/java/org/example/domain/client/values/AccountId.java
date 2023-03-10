package org.example.domain.client.values;

import org.example.generic.Identity;

import java.util.Objects;

public class AccountId extends Identity {

    private AccountId(String value) {
        super(value);
    }

    public AccountId() {}

    public static AccountId of(String value) {
        if (!Objects.equals(value, "")) {
            return new AccountId(value);
        } else throw new IllegalArgumentException("The id must not be an empty string");
    }

}
