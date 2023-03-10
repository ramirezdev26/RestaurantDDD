package org.example.domain.order.events;

import org.example.generic.DomainEvent;

public class WaiterAdded extends DomainEvent {

    private String waiterId;
    private String email;
    private String name;
    private String password;
    private String phone;
    private String username;

    public WaiterAdded(String waiterId, String email, String name, String password, String phone, String username) {
        super("restaurant.order.WaiterAdded");
        this.waiterId = waiterId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.username = username;
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
}
