package org.example.domain.order;

import org.example.domain.order.values.*;
import org.example.generic.Entity;

public class Waiter extends Entity<WaiterId> {

    protected Email email;
    protected WaiterName name;
    protected Password password;
    protected Phone phone;
    protected Username username;

    public Waiter(WaiterId id, Email email, WaiterName name, Password password, Phone phone, Username username) {
        super(id);
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.username = username;
    }

    public Email email() {
        return email;
    }

    public WaiterName name() {
        return name;
    }

    public Password password() {
        return password;
    }

    public Phone phone() {
        return phone;
    }

    public Username username() {
        return username;
    }
}
