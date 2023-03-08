package org.example.domain.client.values;

import org.example.generic.Identity;

public class AccountId extends Identity {

    private AccountId(String value) {
        super(value);
    }

    public AccountId() {}

    public static AccountId of(String value) {
        return new AccountId(value);
    }

}
