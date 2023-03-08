package org.example.domain.client.commands;

import org.example.generic.Command;

public class AddAccountCommand extends Command {

    private String accountId;
    private String email;
    private String password;
    private String username;
    private String clientId;

    public AddAccountCommand(String accountId, String email, String password, String username, String clientId) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.username = username;
        this.clientId = clientId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getClientId() {
        return clientId;
    }
}
