package org.example.domain.client;


import org.example.domain.client.values.*;
import org.example.generic.Entity;

public class Account extends Entity<AccountId> {

    private Email email;
    private Password password;
    private Username username;

    public Account(AccountId id, Email email, Password password, Username username) {
        super(id);
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Username getUsername() {
        return username;
    }
}
