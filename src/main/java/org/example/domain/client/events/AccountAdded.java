package org.example.domain.client.events;

import org.example.generic.DomainEvent;

public class AccountAdded extends DomainEvent {

    private String accountId;
    private String email;
    private String password;
    private String username;


    public AccountAdded(String accountId, String email, String password, String username) {
        super("restaurant.client.accountAdded");
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getAccountId() { return accountId; }

    public String getEmail() { return email; }

    public String getPassword() { return password; }

    public String getUsername() { return username; }

}
