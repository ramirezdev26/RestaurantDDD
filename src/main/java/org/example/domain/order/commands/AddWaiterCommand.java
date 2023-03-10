package org.example.domain.order.commands;

import org.example.generic.Command;

public class AddWaiterCommand extends Command {

    private String waiterId;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String username;
    private String orderId;

    public AddWaiterCommand(String waiterId, String email, String name, String password, String phone, String username, String orderId) {
        this.waiterId = waiterId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.username = username;
        this.orderId = orderId;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getOrderId() {
        return orderId;
    }
}
